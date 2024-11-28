import pandas as pd

file_path = 'C:/Users/Admin/Downloads/Datasets(1)/Datasets/Covid Vaccine Statewise.csv'
covid_data = pd.read_csv(file_path)

print(covid_data.head(), "\n")

print(covid_data.describe())

no_males_vaccinated = covid_data['Male (Doses Administered)'].sum()

print(f"total males vaccinated are : {no_males_vaccinated}") 


no_femlaes_vaccinated = covid_data['Female (Doses Administered)'].sum()

print(f"Total females vaccinated are : {no_femlaes_vaccinated}")