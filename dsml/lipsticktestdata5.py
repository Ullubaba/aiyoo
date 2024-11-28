import pandas as pd
from sklearn.tree import DecisionTreeClassifier
from sklearn.preprocessing import LabelEncoder

file_path = 'C:/Users/Admin/Downloads/Datasets(1)/Datasets/Lipstick.csv'
Lipstick_data = pd.read_csv(file_path)

df = pd.DataFrame(Lipstick_data)

encoders = {}
for column in df.columns:
    encoders[column] = LabelEncoder()
    df[column] = encoders[column].fit_transform(df[column])
    
X = df[["Age", "Income", "Gender", "Ms"]]
y = df["Buys"]

clf = DecisionTreeClassifier(criterion="entropy", random_state=0)
clf.fit(X, y)

test_data = {
    "Age": ["<21"],
    "Income": ["Low"],
    "Gender": ["Female"],
    "Ms": ["Married"]
}

test_data_encoded = {col: encoders[col].transform(test_data[col]) for col in test_data}
test_data_df = pd.DataFrame(test_data_encoded)

prediction = clf.predict(test_data_df)
result = encoders["Buys"].inverse_transform(prediction)

print(f"Decision for test data {list(test_data.values())}: {result[0]}")
