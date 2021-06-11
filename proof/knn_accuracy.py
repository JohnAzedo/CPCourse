#%%
# Import and read csv
import pandas as pd
from sklearn.neighbors import KNeighborsClassifier

path: str = '/home/johnazedo/Documents/UFRN/CPCourse/datasets/diabetes.csv'
dataframe = pd.read_csv(path)

# %%
# Separate outcome and predictors
outcome = dataframe.iloc[:, 0:8]
predictors = dataframe.iloc[:, 8]

# %%
# Fit
knn = KNeighborsClassifier(n_neighbors=3)
knn.fit(outcome, predictors)

#%%
# Testing
print(knn.predict([[6,162,62,0,0,24.3,0.178,50]]))

# %%
