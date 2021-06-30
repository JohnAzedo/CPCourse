
#%%

# Import and read csv
import pandas as pd
path: str = '/home/johnazedo/Documents/UFRN/CPCourse/knn/datasets/diabetes.csv'
dataframe = pd.read_csv(path)


#%%

def min_and_max(x):
    return pd.Series(index=['min','max'],data=[x.min(),x.max()])


dataframe.apply(min_and_max)
# %%
