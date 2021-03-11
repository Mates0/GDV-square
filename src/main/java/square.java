import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL33;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

public class square {

    public static final float[] vertices = {
            -0.5f, -0.5f, 1.0f,
            0.5f, -0.5f, 1.0f,
            0.5f, 0.5f, 1.0f,
            -0.5f, -0.5f, 1.0f,
            -0.5f, 0.5f, 1.0f,
            0.5f, 0.5f, 1.0f
    };

    public static int triangleVaoId;

    public static void init() {

        Shader.initShaders();

        int triangleVboId = GL33.glGenBuffers();
        triangleVaoId = GL33.glGenVertexArrays();

        GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, triangleVboId);
        GL33.glBindVertexArray(triangleVaoId);

        FloatBuffer fb = BufferUtils.createFloatBuffer(vertices.length)
                .put(vertices)
                .flip();

        GL33.glBufferData(GL33.GL_ARRAY_BUFFER, fb, GL33.GL_STATIC_DRAW);
        GL33.glVertexAttribPointer(0, 3, GL33.GL_FLOAT, false, 0, 0);
        GL33.glEnableVertexAttribArray(0);

        MemoryUtil.memFree(fb);
    }

    public static void render() {
        GL33.glUseProgram(Shader.shaderProgramId);
        GL33.glBindVertexArray(triangleVaoId);
        GL33.glDrawArrays(GL33.GL_TRIANGLES, 0, vertices.length / 3);
    }

    public static void update() {
    }

}