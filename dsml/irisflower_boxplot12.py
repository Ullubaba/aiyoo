import math
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

file_path = 'C:/Users/Admin/Downloads/Datasets(1)/Datasets/IRIS.csv'
IRIS_data = pd.read_csv(file_path)

print(IRIS_data.head())

plt.figure(figsize=(12,8))
for i,column in enumerate(IRIS_data.columns[:-1]):
    plt.subplot(2,2,i+1)
    sns.boxplot(x=IRIS_data[column],color='skyblue')
    plt.title(f'Boxplot of {column}')
    
plt.tight_layout()
plt.show()

summary_stats = IRIS_data.describe()
outliers = {}

for column in IRIS_data.columns[:-1]:
    q1 = IRIS_data[column].quantile(0.25)
    q3 = IRIS_data[column].quantile(0.75)
    
    iqr = q3-q1
    
    lower_bound = q1 - 1.5*iqr
    upper_bound = q3 + 1.5*iqr
    
    outliers[column] = IRIS_data[ (IRIS_data[column] < lower_bound) | (IRIS_data[column] > upper_bound)][column]
    
print(summary_stats)
print("\n OUTLIER VALUES: \n")
for feature,outlier_data in outliers.items():
    print(f"{feature} :\n {outlier_data}")