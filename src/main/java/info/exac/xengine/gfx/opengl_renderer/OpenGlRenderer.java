package info.exac.xengine.gfx.opengl_renderer;

import info.exac.xengine.gfx.common.Rgba;
import info.exac.xengine.gfx.common.RgbaTexture;
import info.exac.xengine.gfx.g2d.gui.visual.VisualComponent;
import info.exac.xengine.gfx.g2d.Stage2D;
import info.exac.xengine.gfx.g2d.TileMap;
import info.exac.xengine.gfx.g2d.Vertex2D;
import info.exac.xengine.gfx.g2d.elements.*;
import info.exac.xengine.gfx.g2d.gui.common.IDrawable;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;



/**
 * @author exac
 * @date 02/02/2018 16:16
 */
public class OpenGlRenderer {


    public static final int TEXTURE_UNBIND = 0;



    public void renderStage(Stage2D stage) {
        for (Abstract2DElement element : stage.getElements()) {
            renderElement(element);
        }

        for (IDrawable drawable : stage.getDrawableComponents()) {

            if (drawable instanceof VisualComponent) {
                VisualComponent visualComponent = (VisualComponent) drawable;
                if (visualComponent.isVisible()) {
                    if (visualComponent.isRedrawRequested()) {
                        visualComponent.redraw();
                        visualComponent.setRedrawRequested(false);
                    }

                    renderComponent(visualComponent);
                }
            } else {
                drawable.draw().forEach(this::renderElement);
            }
        }

    }



    private void renderComponent(IDrawable component) {
        for (Abstract2DElement element : component.draw()) {
            renderElement(element);
        }
    }



    private void renderElement(Abstract2DElement element) {
        if (element instanceof Point2D) {
            renderPoint((Point2D) element);
        } else if (element instanceof Line2D) {
            renderLine((Line2D) element);
        } else if (element instanceof Triangle2D) {
            renderTriangle((Triangle2D) element);
        } else if (element instanceof Quad2D) {
            renderQuad((Quad2D) element);
        } else if (element instanceof OutlinedRectangle2D) {
            renderOutlinedRectangle((OutlinedRectangle2D) element);
        } else if (element instanceof FilledRectangle2D) {
            renderFilledRectangle((FilledRectangle2D) element);
        } else if (element instanceof Circle2D) {
            renderCircle((Circle2D) element);
        } else if (element instanceof AnimatedRectagle2D) {
            renderAnimatedRectangle((AnimatedRectagle2D) element);
        } else if (element instanceof TexturedRectangle2D) {
            renderTexturedRectangle((TexturedRectangle2D) element);
        } else if (element instanceof TileMap) {
            renderTileMap((TileMap) element);
        } else if (element instanceof RasterText) {
            renderRasterText((RasterText) element);
        }
    }




    private void renderPoint(Point2D point) {
        glEnable(GL_POINT_SIZE);
        glPointSize(point.size);

        glBegin(GL_POINTS);
        glColor4d(point.vertex.rgba.red, point.vertex.rgba.green, point.vertex.rgba.blue, point.vertex.rgba.alpha);
        glVertex2d(point.vertex.x, point.vertex.y);
        glEnd();

    }



    private void renderLine(Line2D line) {
        glEnable(GL_LINE_WIDTH);
        glLineWidth(line.size);

        glBegin(GL_LINES);
        renderVertexList(line.verteces);
        glEnd();
    }



    private void renderQuad(Quad2D quad) {
        glBegin(GL_QUADS);
        renderVertexList(quad.verteces);
        glEnd();
    }



    private void renderFilledRectangle(FilledRectangle2D rectangle2D) {
        glBegin(GL_QUADS);
        renderVertexList(rectangle2D.verteces);
        glEnd();
    }



    private void renderTriangle(Triangle2D triangle) {
        glBegin(GL_TRIANGLES);
        renderVertexList(triangle.verteces);
        glEnd();
    }



    private void renderOutlinedRectangle(OutlinedRectangle2D outlinedRectangle) {
        glEnable(GL_LINE_WIDTH);
        glLineWidth(outlinedRectangle.size);

        glBegin(GL_LINES);
        renderVertex(outlinedRectangle.verteces.get(0));
        renderVertex(outlinedRectangle.verteces.get(1));

        renderVertex(outlinedRectangle.verteces.get(1));
        renderVertex(outlinedRectangle.verteces.get(2));

        renderVertex(outlinedRectangle.verteces.get(2));
        renderVertex(outlinedRectangle.verteces.get(3));

        renderVertex(outlinedRectangle.verteces.get(3));
        renderVertex(outlinedRectangle.verteces.get(0));
        glEnd();
    }



    private void renderCircle(Circle2D circle) {
        glBegin(GL_TRIANGLE_FAN);
        renderVertexList(circle.verteces);
        glEnd();
    }



    private void renderVertexList(List<Vertex2D> verteces) {
        for (Vertex2D vertex : verteces) {
            glColor4d(vertex.rgba.red, vertex.rgba.green, vertex.rgba.blue, vertex.rgba.alpha);
            glVertex2d(vertex.x, vertex.y);
        }
    }



    private void renderVertex(Vertex2D vertex) {
        glColor4d(vertex.rgba.red, vertex.rgba.green, vertex.rgba.blue, vertex.rgba.alpha);
        glVertex2d(vertex.x, vertex.y);
    }



    private void renderTexturedRectangle(TexturedRectangle2D texturedRectangle) {

        glBindTexture(GL_TEXTURE_2D, texturedRectangle.getRgbaTexture().getTextureId());

//        glMatrixMode(GL_MODELVIEW);
//        glPushMatrix();
//        glRotated(Math.PI / 2, texturedRectangle.verteces.get(0).x, texturedRectangle.verteces.get(0).y, 0.0);

        glBegin(GL_QUADS);
            glTexCoord2d(0.0, 0.0);
            renderVertex(texturedRectangle.verteces.get(0));
            glTexCoord2d(1.0, 0.0);
            renderVertex(texturedRectangle.verteces.get(1));
            glTexCoord2d(1.0, 1.0);
            renderVertex(texturedRectangle.verteces.get(2));
            glTexCoord2d(0.0, 1.0);
            renderVertex(texturedRectangle.verteces.get(3));
        glEnd();

//        glPopMatrix();

        glBindTexture(GL_TEXTURE_2D, 0);
    }



    private void renderAnimatedRectangle(AnimatedRectagle2D texturedRectangle) {

        glBindTexture(GL_TEXTURE_2D, texturedRectangle.getRgbaTexture().getTextureId());

        int frameCount = texturedRectangle.getRgbaTexture().getFrameCount();
        int frameCountX = texturedRectangle.getRgbaTexture().getFrameCountX();
        int frameCountY = texturedRectangle.getRgbaTexture().getFrameCountY();

        int frameIndex = (int) (System.currentTimeMillis() / 1000) % frameCount;

        int frameIndexX = frameIndex % frameCountX;
        int frameIndexY = frameIndex / frameCountY;


        double frameShiftX = 1.0 / (double) frameCountX;
        double frameShiftY = 1.0 / (double) frameCountY;

        glBegin(GL_QUADS);

            glTexCoord2d(frameIndexX * frameShiftX, frameIndexY * frameShiftY);
            glColor4d(texturedRectangle.a.rgba.red, texturedRectangle.a.rgba.green, texturedRectangle.a.rgba.blue, texturedRectangle.a.rgba.alpha);
            glVertex2d(texturedRectangle.a.x, texturedRectangle.a.y);

            glTexCoord2d( (frameIndexX + 1) * frameShiftX, frameIndexY * frameShiftY);
            glColor4d(texturedRectangle.b.rgba.red, texturedRectangle.b.rgba.green, texturedRectangle.b.rgba.blue, texturedRectangle.b.rgba.alpha);
            glVertex2d(texturedRectangle.b.x, texturedRectangle.b.y);


            glTexCoord2d((frameIndexX + 1) * frameShiftX, (frameIndexY + 1) * frameShiftY);
            glColor4d(texturedRectangle.c.rgba.red, texturedRectangle.c.rgba.green, texturedRectangle.c.rgba.blue, texturedRectangle.c.rgba.alpha);
            glVertex2d(texturedRectangle.c.x, texturedRectangle.c.y);

            glTexCoord2d(frameIndexX * frameShiftX, (frameIndexY + 1) * frameShiftY);
            glColor4d(texturedRectangle.d.rgba.red, texturedRectangle.d.rgba.green, texturedRectangle.d.rgba.blue, texturedRectangle.d.rgba.alpha);
            glVertex2d(texturedRectangle.d.x, texturedRectangle.d.y);

        glEnd();

        glBindTexture(GL_TEXTURE_2D, 0);
    }


    private void renderTileMap(TileMap tileMap) {

        int tileCountX = tileMap.getRgbaTexture().getTileCountX();
        int tileCountY = tileMap.getRgbaTexture().getTileCountY();

        for (int x = 0; x < tileMap.getTileCountX(); x++) {
            for (int y = 0; y < tileMap.getTileCountY(); y++) {
                renderTile(
                        x * tileMap.getTileWidth() + tileMap.getMapPositionX(),
                        y * tileMap.getTileHeight() + tileMap.getMapPositionY(),
                        tileMap.getTileWidth(),
                        tileMap.getTileHeight(),
                        tileMap.getRgbaTexture(),
                        tileMap.getValues()[x][y],
                        1.0 / (double) tileCountX,
                        1.0 / (double) tileCountY,
                        tileCountX);
            }
        }
    }



    private void renderTile(double x, double y, double tileWidth, double tileHeight,
                            RgbaTexture rgbaTexture, int frameIndex, double frameShiftX, double frameShiftY, int tilesCountX) {
        renderTile(x, y, tileWidth, tileHeight, rgbaTexture, frameIndex, frameShiftX, frameShiftY, tilesCountX,
                Rgba.WHITE);
    }



    private void renderTile(double x, double y, double tileWidth, double tileHeight,
                            RgbaTexture rgbaTexture, int frameIndex, double frameShiftX, double frameShiftY, int tilesCountX,
                            Rgba color) {
        renderTile(x, y, tileWidth, tileHeight, rgbaTexture, frameIndex, frameShiftX, frameShiftY, tilesCountX,
                color, color, color, color);
    }



    private void renderTile(double x, double y, double tileWidth, double tileHeight,
                            RgbaTexture rgbaTexture, int frameIndex, double frameShiftX, double frameShiftY, int tilesCountX,
                            Rgba topLeftColor, Rgba topRightColor, Rgba bottomRightColor, Rgba bottomLeftColo) {
        glBindTexture(GL_TEXTURE_2D, rgbaTexture.getTextureId());

        int frameIndexX = frameIndex % tilesCountX;
        int frameIndexY = frameIndex / tilesCountX;

        glBegin(GL_QUADS);
            glColor4d(topLeftColor.red, topLeftColor.green, topLeftColor.blue, topLeftColor.alpha);
            glTexCoord2d(frameIndexX * frameShiftX, frameIndexY * frameShiftY);
            glVertex2d(x, y);

            glColor4d(topRightColor.red, topRightColor.green, topRightColor.blue, topRightColor.alpha);
            glTexCoord2d((frameIndexX + 1) * frameShiftX, frameIndexY * frameShiftY);
            glVertex2d(x + tileWidth, y);

            glColor4d(bottomRightColor.red, bottomRightColor.green, bottomRightColor.blue, bottomRightColor.alpha);
            glTexCoord2d((frameIndexX + 1) * frameShiftX, (frameIndexY + 1) * frameShiftY);
            glVertex2d(x + tileWidth, y + tileHeight);

            glColor4d(bottomLeftColo.red, bottomLeftColo.green, bottomLeftColo.blue, bottomLeftColo.alpha);
            glTexCoord2d(frameIndexX * frameShiftX, (frameIndexY + 1) * frameShiftY);
            glVertex2d(x, y + tileHeight);
        glEnd();

        glBindTexture(GL_TEXTURE_2D, TEXTURE_UNBIND);
    }



    public void renderRasterText(RasterText rasterText) {

        char[] chars = rasterText.getValue().toCharArray();
        int textPosX = 0;
        int textPosY = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '\n') {
                textPosY++;
                textPosX = 0;
                continue;
            }

            double glyphWidth = rasterText.getRgbaTexture().getTileWidth();
            if (rasterText.getGlyphWidth() > 0) {
                glyphWidth = 16;
            }

            double glyphHeight = rasterText.getRgbaTexture().getTileHeight();
            if (rasterText.getGlyphHeight() > 0) {
                glyphHeight = 24;
            }

            renderTile(
                    rasterText.getX() + (textPosX * glyphWidth),
                    rasterText.getY()+ (textPosY * glyphHeight),
                    glyphWidth,
                    glyphHeight,
                    rasterText.getRgbaTexture(),
                    (int) chars[i],
                    1.0 / (double) rasterText.getRgbaTexture().getTileCountX(),
                    1.0 / (double) rasterText.getRgbaTexture().getTileCountY(),
                    rasterText.getRgbaTexture().getTileCountX(),
                    rasterText.getRgba()
            );

            textPosX++;
        }

    }




}
