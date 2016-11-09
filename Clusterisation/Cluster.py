class Cluster:
    def __init__(self, data1, data2=None):
        if data2 == None:
            self.size = 1
        else:
            self.size = data1.size + data2.size

        self.carac = []
        if self.size == 1:
            self.type = data1.iat[0]
            print(self.type)
            for index in range(1, 18):
                self.carac.append(data1.iat[index])
        else:
            for index in range(0, 17):
                self.carac.append((data1.carac[index]*data1.size + data2.carac[index]*data2.size)/self.size)



