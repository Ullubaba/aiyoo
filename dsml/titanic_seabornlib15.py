import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

file_path = 'C:/Users/Admin/Downloads/Datasets(1)/Datasets/Titanic.csv'
titanic_data = pd.read_csv(file_path)

print(titanic_data.head())

plt.figure(figsize=(8,5))
sns.countplot(data=titanic_data,x='Survived',hue='Sex',palette='cool')
plt.title("Survival rate by Gender")
plt.xlabel("Survived (0 = No,1 = Yes)")
plt.ylabel("count")
plt.legend(title="Gender")
plt.show()

plt.figure(figsize=(8,5))
sns.countplot(data=titanic_data,x="Pclass",hue="Survived",palette="viridis")
plt.title("Survival rate by Passenger class")
plt.xlabel("Passenger class (1 = 1st,2 = 2nd,3 = 3rd)")
plt.ylabel("Count")
plt.legend(title="Survived")
plt.show()

plt.figure(figsize=(8,5))
sns.kdeplot(data=titanic_data,x="Age",hue="Survived",fill=True,palette="crest",alpha=0.6)
plt.title("Age Distribution by Survival rate")
plt.xlabel("Age")
plt.ylabel("Density")
plt.show()

plt.figure(figsize=(8,5))
sns.countplot(data=titanic_data,x="Pclass",palette="pastel")
plt.title("Passenger Class by distribution")
plt.xlabel("Passenger Class (1=1st,2=2nd,3=3rd)")
plt.ylabel("count")
plt.show()