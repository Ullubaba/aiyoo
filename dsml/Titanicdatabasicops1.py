import pandas as pd

csv_file_path = 'titanic.csv' 
data_csv = pd.read_csv(csv_file_path)


print("Data from CSV:")
print(data_csv.head())


excel_file_path = 'titanic.xlsx' 
data_excel = pd.read_excel(excel_file_path)

print("\nData from Excel:")
print(data_excel.head())

print("\nSelected Columns (Name, Age):")
print(data_csv[['Name', 'Age']].head())

sorted_data = data_csv.sort_values(by='Age', ascending=True)
print("\nData sorted by Age:")
print(sorted_data[['Name', 'Age']].head())

print("\nDescription of numerical attributes:")
print(data_csv.describe())

print("\nDescription of categorical attributes:")
print(data_csv.describe(include=['O']))

print("\nData types of each column:")
print(data_csv.dtypes)
