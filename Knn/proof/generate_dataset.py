# %%
# Initial data
LIMITS = [('Pregnancies', 0, 17, 1), ('Glucose', 0, 199, 1), ('BloodPressure', 0, 122, 1), ('SkinThickness', 0, 99, 1), ('Insulin', 0, 846, 1), ('BMI', 0, 671, 0.1), ('DiabetesPedigreeFunction', 78, 2420, 0.001), ('Age', 21, 81, 1)]
NUMBER_OF_ROWS = 10000000

# %%
# Generate number
import random

data = {}
for limit in LIMITS:
    index = limit[0]; minimum = limit[1]; maximum = limit[2]; step = limit[3]
    data[index] = []

    for i in range(NUMBER_OF_ROWS):
        value = random.randint(minimum, maximum)*step

        if(index=='BMI'):
            data[index].append(float("{:.1f}".format(value)))

        elif(index=='DiabetesPedigreeFunction'):
            data[index].append(float("{:.3f}".format(value)))
        
        else:
            data[index].append(value)

data['Outcome'] = []
for i in range(NUMBER_OF_ROWS):
    data['Outcome'].append(random.choice([0, 1]))

# %%
# Put data in dataframe
import pandas as pd

dataframe = pd.DataFrame(data)
dataframe.to_csv('example.csv', index=False);
