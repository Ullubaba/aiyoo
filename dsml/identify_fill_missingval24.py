import numpy as np
import pandas as pd

file_path = 'C:/Users/Admin/Downloads/Datasets(1)/Datasets/Titanic.csv'
data = pd.read_csv(file_path)

# Display unique values in each column
print("Unique values in dataset : \n")
print(data.nunique())

# Checking the data types of each column
print("Checking the data types of each column : \n")
print(data.dtypes)

# Converting data types
data['Age'] = data['Age'].fillna(data['Age'].median()).astype(int)
data['Pclass'] = data['Pclass'].astype(str)
data['Fare'] = data['Fare'].astype('float32')

# Identifying missing values in each column
print("\nMissing values count in each column before filling : \n")
print(data.isnull().sum())

# Filling missing values
data['Age'] = data['Age'].fillna(data['Age'].median())
data['Embarked'] = data['Embarked'].fillna(data['Embarked'].mode()[0])
data['Fare'] = data['Fare'].fillna(data['Fare'].median())
data['Cabin'] = data['Cabin'].fillna('Unknown')

# Checking missing values after filling
print("\nMissing values count after filling : \n")
print(data.isnull().sum())

# Print first few rows after transformation
print(data.head())

# Save cleaned data
data.to_csv("titanic_cleaned_data.csv", index=False)
print("\nCleaned dataset saved to 'titanic_cleaned_data.csv'.")
