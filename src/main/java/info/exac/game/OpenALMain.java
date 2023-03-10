package info.exac.game;

/**
 * @author exac
 * @date 13/02/2018 14:18
 */
import org.lwjgl.*;
import org.lwjgl.openal.*;
import org.lwjgl.stb.*;

import java.io.*;
import java.nio.*;
import java.util.*;

import static info.exac.xengine.sfx.IOUtil.ioResourceToByteBuffer;
import static info.exac.xengine.sfx.OpenALInfo.checkALCError;
import static info.exac.xengine.sfx.OpenALInfo.checkALError;
import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.openal.ALC11.*;
import static org.lwjgl.openal.EXTThreadLocalContext.*;
import static org.lwjgl.stb.STBVorbis.*;
import static org.lwjgl.system.MemoryUtil.*;

public final class OpenALMain {

    private OpenALMain() {
    }

    public static void main(String[] args) {
        long device = alcOpenDevice((ByteBuffer)null);
        if (device == NULL) {
            throw new IllegalStateException("Failed to open the default device.");
        }

        ALCCapabilities deviceCaps = ALC.createCapabilities(device);

        if (!deviceCaps.OpenALC10) {
            throw new IllegalStateException();
        }

        System.out.println("OpenALC10: " + deviceCaps.OpenALC10);
        System.out.println("OpenALC11: " + deviceCaps.OpenALC11);
        System.out.println("caps.ALC_EXT_EFX = " + deviceCaps.ALC_EXT_EFX);

        if (deviceCaps.OpenALC11) {
            List<String> devices = ALUtil.getStringList(NULL, ALC_ALL_DEVICES_SPECIFIER);
            if (devices == null) {
                checkALCError(NULL);
            } else {
                for (int i = 0; i < devices.size(); i++) {
                    System.out.println(i + ": " + devices.get(i));
                }
            }
        }

        String defaultDeviceSpecifier = Objects.requireNonNull(alcGetString(NULL, ALC_DEFAULT_DEVICE_SPECIFIER));
        System.out.println("Default device: " + defaultDeviceSpecifier);

        long context = alcCreateContext(device, (IntBuffer)null);
        alcSetThreadContext(context);
        AL.createCapabilities(deviceCaps);

        System.out.println("ALC_FREQUENCY: " + alcGetInteger(device, ALC_FREQUENCY) + "Hz");
        System.out.println("ALC_REFRESH: " + alcGetInteger(device, ALC_REFRESH) + "Hz");
        System.out.println("ALC_SYNC: " + (alcGetInteger(device, ALC_SYNC) == ALC_TRUE));
        System.out.println("ALC_MONO_SOURCES: " + alcGetInteger(device, ALC_MONO_SOURCES));
        System.out.println("ALC_STEREO_SOURCES: " + alcGetInteger(device, ALC_STEREO_SOURCES));

        try {
            testPlayback();
        } finally {
            alcMakeContextCurrent(NULL);
            alcDestroyContext(context);
            alcCloseDevice(device);
        }
    }

    private static void testPlayback() {
        // generate buffers and sources
        int buffer = alGenBuffers();
        checkALError();

        int source = alGenSources();
        checkALError();

        try (STBVorbisInfo info = STBVorbisInfo.malloc()) {
            ShortBuffer pcm = readVorbis("sfx/footsteps.ogg", 32 * 1024, info);

            //copy to buffer
            alBufferData(buffer, info.channels() == 1 ? AL_FORMAT_MONO16 : AL_FORMAT_STEREO16, pcm, info.sample_rate());
            checkALError();
        }

        //set up source input
        alSourcei(source, AL_BUFFER, buffer);
        checkALError();

        //lets loop the sound
        alSourcei(source, AL_LOOPING, AL_FALSE);
        checkALError();

        //play source 0
        alSourcePlay(source);
        checkALError();

        //wait 5 secs
        try {
            System.out.println("Waiting 5 seconds for sound to complete");
            Thread.sleep(1000);

            System.out.println(isPlaying(source));

            Thread.sleep(4000);

            System.out.println(isPlaying(source));

            Thread.sleep(1000);


        } catch (InterruptedException ignored) {
        }

        //stop source 0
        alSourceStop(source);
        checkALError();

        //delete buffers and sources
        alDeleteSources(source);
        checkALError();

        alDeleteBuffers(buffer);
        checkALError();
    }



    static boolean isPlaying(int source) {
        IntBuffer intBuffer = BufferUtils.createIntBuffer(1);
        alGetSourcei(source, AL_SOURCE_STATE, intBuffer);
        return AL_PLAYING == intBuffer.get(0);
    }



    static ShortBuffer readVorbis(String resource, int bufferSize, STBVorbisInfo info) {
        ByteBuffer vorbis;
        try {
            vorbis = ioResourceToByteBuffer(resource, bufferSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        IntBuffer error   = BufferUtils.createIntBuffer(1);
        long      decoder = stb_vorbis_open_memory(vorbis, error, null);
        if (decoder == NULL) {
            throw new RuntimeException("Failed to open Ogg Vorbis file. Error: " + error.get(0));
        }

        stb_vorbis_get_info(decoder, info);

        int channels = info.channels();

        int lengthSamples = stb_vorbis_stream_length_in_samples(decoder);

        ShortBuffer pcm = BufferUtils.createShortBuffer(lengthSamples);

        pcm.limit(stb_vorbis_get_samples_short_interleaved(decoder, channels, pcm) * channels);
        stb_vorbis_close(decoder);

        return pcm;
    }

}