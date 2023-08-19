# Developed by hedrax <Alhossien.waly@ejust.edu.eg>

# Copyright (c) 2023 Hedrax
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:

import pickle
import numpy as np
def modelPredict(pickle_bytes, x1,x2,x3,x4):
    try:
        python_bytes = bytearray(pickle_bytes)
        python_bytes = bytes(python_bytes)

        model = pickle.loads(python_bytes)

        input_data = np.array([x1, x2, x3, x4]).reshape(1, -1)

        # Make predictions using the loaded model
        predictions = model.predict(input_data)

        return predictions
    except Exception as e:
        return "Error processing data: " + str(e)



