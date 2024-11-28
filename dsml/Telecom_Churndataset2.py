import pandas as pd

file_path = 'C:/Users/Admin/Downloads/Datasets(1)/Datasets/Telecom Churn.csv'
telecom_data = pd.read_csv(file_path)

numeric_data = telecom_data.select_dtypes(include=['int64','float64'])

print("Minimum values in the dataset: \n", numeric_data.min(), "\n")
print("Maximum values in the dataset: \n", numeric_data.max(), "\n")
print("Range of the dataset: \n", numeric_data.max() - numeric_data.min(), "\n")
print("mean value of the dataset : \n", numeric_data.mean(), "\n")
print("Standard deviation of the dataset: \n", numeric_data.std(), "\n")
print("Vairance of the dataset: \n", numeric_data.var(), "\n")

print("25th percentile: \n", numeric_data.quantile(0.25), "\n")
print("50th percentile: \n", numeric_data.quantile(0.50), "\n")
print("75th percentile: \n", numeric_data.quantile(0.75), "\n")