import pandas as pd
import sys

# Force UTF-8 encoding for output in the terminal
sys.stdout.reconfigure(encoding='utf-8')

# File path to the dataset
file_path = 'C:/Users/Admin/Downloads/Datasets(1)/Datasets/House Data.csv'
data = pd.read_csv(file_path)

# Select categorical and quantitative variables
categorical_var = "Category"
quantitative_var = "price"

print("Dataset Overview : \n", data.head(), "\n")

# Handle the 'price' column if it contains non-numeric values
# Clean the 'price' column by removing non-numeric characters and converting to float
data[quantitative_var] = (
    data[quantitative_var]
    .str.replace(r'[^\d.]', '', regex=True)
    .astype(float)
)

# Provide summary statistics for the selected variables
grouped_summary_stats = data.groupby(categorical_var)[quantitative_var].agg(
    ['mean', 'median', 'min', 'max', 'std']
)

# Print summary statistics
print(f"Summary statistics of {quantitative_var} grouped by {categorical_var}:\n")
print(grouped_summary_stats)

# Save the summary statistics to a CSV file
output_file = "Summary_stats.csv"
grouped_summary_stats.to_csv(output_file, encoding='utf-8')
print(f"\nSummary statistics saved to {output_file}")

