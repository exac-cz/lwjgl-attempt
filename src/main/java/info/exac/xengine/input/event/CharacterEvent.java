package info.exac.xengine.input.event;

/**
 * @author exac
 * @date 09/02/2018 13:47
 */
public class CharacterEvent extends Event {

    private int codepoint;

    public CharacterEvent(int codepoint) {
        this.codepoint = codepoint;
    }



    @Override
    public String toString() {
        return "Character event: codepoint = " + codepoint + " '" + (char) codepoint + "'";
    }



    public char getChar() {
        return (char) codepoint;
    }





    public int getCodepoint() {
        return codepoint;
    }


}
