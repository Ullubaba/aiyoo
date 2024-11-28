import pandas as pd
import numpy as np

# Data as provided
data = {
    'Age': ['Young', 'Young', 'Young', 'Middle', 'Old', 'Old', 'Old', 'Old', 'Middle', 'Young', 
            'Young', 'Young', 'Old', 'Young', 'Middle', 'Middle', 'Middle', 'Young'],
    'Income': ['High', 'High', 'High', 'High', 'Medium', 'Low', 'Low', 'Low', 'Low', 'Medium', 
               'Low', 'Medium', 'Medium', 'Medium', 'Low', 'Medium', 'High', 'Medium'],
    'Married': ['No', 'No', 'No', 'No', 'No', 'Yes', 'Yes', 'Yes', 'Yes', 'No', 'Yes', 'No', 
                'No', 'Yes', 'Yes', 'No', 'No', 'No'],
    'Health': ['Fair', 'Good', 'Fair', 'Fair', 'Fair', 'Fair', 'Good', 'Fair', 'Good', 'Fair', 
               'Fair', 'Fair', 'Fair', 'Good', 'Fair', 'Fair', 'Fair', 'Good'],
    'Class': ['No', 'No', 'No', 'Yes', 'Yes', 'Yes', 'Yes', 'Yes', 'Yes', 'No', 'No', 'No', 
              'Yes', 'Yes', 'Yes', 'No', 'No', 'Yes']
}

# Creating the DataFrame
df = pd.DataFrame(data)

# Frequency Table for 'Age'
age_freq = df['Age'].value_counts()
print("Frequency Table for 'Age':\n", age_freq)

# Calculate the Entropy of the target variable ('Class') for the original dataset
class_freq = df['Class'].value_counts()
class_prob = class_freq / len(df)

# Entropy for the original dataset
entropy_before = -np.sum(class_prob * np.log2(class_prob))
print("\nEntropy of the original dataset (before split):", entropy_before)

# Group by 'Age' and calculate the entropy for each group
def calculate_entropy(group):
    class_freq_group = group['Class'].value_counts()
    class_prob_group = class_freq_group / len(group)
    return -np.sum(class_prob_group * np.log2(class_prob_group))

# Compute the entropy for each Age group
age_groups = df.groupby('Age')
entropy_after_split = 0
total_rows = len(df)

for age, group in age_groups:
    group_entropy = calculate_entropy(group)
    weight = len(group) / total_rows
    entropy_after_split += weight * group_entropy

print("\nEntropy after splitting by 'Age':", entropy_after_split)

# Calculate Information Gain
information_gain = entropy_before - entropy_after_split
print("\nInformation Gain when splitting on 'Age':", information_gain)
