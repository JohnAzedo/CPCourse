#%%
# Import and read csv
import pandas as pd
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import accuracy_score

path: str = '/home/johnazedo/Documents/UFRN/CPCourse/knn/datasets/diabetes.csv'
dataframe = pd.read_csv(path)

# %%
# Separate outcome and predictors
predictors = dataframe.iloc[:, 0:8]
outcome = dataframe.iloc[:, 8]
del dataframe

# %%
# Fit
knn = KNeighborsClassifier(n_neighbors=14, metric='euclidean')
knn.fit(predictors, outcome)
del predictors
del outcome

#%%
# Testing
path: str = '/home/johnazedo/Documents/UFRN/CPCourse/knn/datasets/test_diabetes.csv'
dataframe = pd.read_csv(path)
traning_data = dataframe.iloc[:, 0:8]
values_target = dataframe.iloc[:, 8]
result = knn.predict(traning_data)

acc = accuracy_score(result, values_target)
result

# %%
