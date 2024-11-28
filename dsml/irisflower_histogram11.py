import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

file_path = 'C:/Users/Admin/Downloads/Datasets(1)/Datasets/IRIS.csv'  
iris_data = pd.read_csv(file_path)

feature_types = iris_data.dtypes
print("Features and their data types:")
print(feature_types)

plt.figure(figsize=(12, 8))

features = iris_data.columns[:-1]

for i, feature in enumerate(features):
    plt.subplot(2, 2, i + 1)
    sns.histplot(iris_data[feature], kde=True, bins=20, color='blue')
    plt.title(f"Histogram of {feature}")
    plt.xlabel(feature)
    plt.ylabel('Frequency')

plt.tight_layout()
plt.show()
