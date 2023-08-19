// Developed by hedrax <Alhossien.waly@ejust.edu.eg>

package com.example.flightmlapplication.model_caller;


import android.content.Context;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.flightmlapplication.utils.InputData;

import java.io.IOException;
import java.io.InputStream;


public class PythonRunner {
    byte[] buffer;
    Python python;
    public PythonRunner(Context context) throws IOException {

        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }


        InputStream file = context.getAssets().open("model.pkl");
        // Calculate the length of the input stream
        int length = file.available();

        // Read the input stream into a ByteBuffer
        buffer = new byte[length];
        int bytesRead = file.read(buffer);
        //checking if the file was read right
        if (bytesRead != length) {
            throw new IOException("Failed to read the entire asset");
        }
        // Call the Python function and assign it to class variable
        python = Python.getInstance();

    }

    //fn to take the actual input and output predicted value
    public float predict(InputData inputData)
    {
        return python.getModule("__init__")
                .callAttr("modelPredict",  buffer, inputData.getX1(), inputData.getX2(), inputData.getX3(), inputData.getX4()).asList().get(0).toFloat();
    }
}
