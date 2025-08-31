from bs4 import BeautifulSoup
from pprint import pprint
import datetime
import json

def getDate():
    returnObject = []
    
    with open("semester.html", "r", encoding="utf-8") as f:
        html_content = f.read()

    soup = BeautifulSoup(html_content, "html.parser")
    table = soup.find("table")
    

    for row in table.find_all("tr"):
        cols = row.find_all("td")
        sem = {}
        
        sem["name"]  = cols[0].text.strip()
        
        start, *end = cols[1].text.strip().split("/")
        sem["startYear"] = int(start)
        sem["endYear"] = int(end[0]) if end else int(start)
        
        date = cols[2].text.strip()
        startDate, endDate = date.replace(".", "").split("–")


        startDate = startDate.strip()
        endDate = endDate.strip()

        dateObjOne = datetime.datetime.strptime(startDate, "%d%m%Y")
        sem["startDate"] = dateObjOne.strftime("%Y-%m-%d")

        dateObjTwo = datetime.datetime.strptime(endDate, "%d%m%Y")
        sem["endDate"] = dateObjTwo.strftime("%Y-%m-%d")

        returnObject.append(sem)

        
    return returnObject


with open ("../src/main/resources/sample.json", "w") as f:
    json.dump (getDate(), f)
