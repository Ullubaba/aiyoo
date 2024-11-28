import pandas as pd
import matplotlib.pyplot as plt

file_path = 'C:/Users/Admin/Downloads/Datasets(1)/Datasets/House Data.csv'
House_data = pd.read_csv(file_path)

numeric_data = House_data.select_dtypes(['int64','float64'])

std_dev = numeric_data.std()
print("Standard deviation of the dataset: \n", std_dev, "\n")

variance = numeric_data.var()
print("Variance of the dataset: \n", variance, "\n")

print("25th percentile of the dataset: \n", numeric_data.quantile(0.25), "\n")
print("50th percentile of the dataset: \n",numeric_data.quantile(0.50), "\n")
print("75th percentile of the dataset: \n", numeric_data.quantile(0.75), "\n")

numeric_data.hist(figsize=(12,10), bins = 30,edgecolor = 'black')
plt.suptitle('Feature distributions')
plt.tight_layout()
plt.show()