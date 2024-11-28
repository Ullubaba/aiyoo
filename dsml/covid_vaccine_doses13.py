import pandas as pd

file_path = 'C:/Users/Admin/Downloads/Datasets(1)/Datasets/Covid Vaccine Statewise.csv'
covid_data = pd.read_csv(file_path)

print("\n", covid_data.head(), "\n")

print("\n", covid_data.describe(), "\n")

first_dose_vaccination_statewise = covid_data.groupby('State')['First Dose Administered'].sum()
print("Total first doses vaccinated statewise is equal to : " , first_dose_vaccination_statewise)

second_dose_vaccination_statewise = covid_data.groupby('State')['Second Dose Administered'].sum()
print("Total second doses vaccinated statewise are : " , second_dose_vaccination_statewise)

print(covid_data.columns)

total_doses_admisitered_statewise = covid_data.groupby('State')['Total Doses Administered'].sum()
print("Total doses administered in India statewise are : ", total_doses_admisitered_statewise)

