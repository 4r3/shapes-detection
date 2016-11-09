import pandas as pd
from Cluster import Cluster


def normalize(data, min_tab, max_tab):
    for index, row in min_tab.iteritems():
        data.at[index] = (data.at[index] - min_tab.at[index]) / (max_tab.at[index] - min_tab.at[index])
    return data


input_file = "./zoo.data"

df = pd.read_csv(input_file, header=None)
dcat = df.describe().transpose()

mins = dcat['min']
maxs = dcat['max']

clusters = []

for index, row in df.iterrows():
    cluster = Cluster(data1=normalize(row, mins, maxs))
    clusters.append(cluster)