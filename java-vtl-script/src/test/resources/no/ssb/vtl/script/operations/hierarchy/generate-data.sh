#!/bin/sh

# kostra0a_2015.json
#curl -q -s -H"Accept: application/ssb.dataset+json" "http://al-kostra-app-utv:7080/api/v2/data/KOSTRA0A:425215?access_token=[TOKENHERE]&columns=PERIODE,BYDEL,KONTOKLASSE,REGION,ART_SEKTOR,FUNKSJON_KAPITTEL,BELOP&sort=PERIODE,BYDEL,REGION,FUNKSJON_KAPITTEL,KONTOKLASSE,ART_SEKTOR" | pv | jq "[.data[] | [.[5]] + .[1:5] + .[6:8]]" > kostra0a_2015.json


jq "[.[] | select(.[3] == \"202\" and .[4] == \"1\")]" < kostra0a_2016.json > kostra0a_grunnskole_driftregnskap_2016.json
jq "[.[] | select(.[3] == \"202\" and .[4] == \"1\")]" < kostra0a_2015.json > kostra0a_grunnskole_driftregnskap_2015.json

# Compress
bro --quality 9 < kostra0a_2015.json > kostra0a_2015.json.bro
bro --quality 9 < kostra0a_2016.json > kostra0a_2016.json.bro
bro --quality 9 < kostra0a_grunnskole_driftregnskap_2016.json > kostra0a_grunnskole_driftregnskap_2016.json.bro
bro --quality 9 < kostra0a_grunnskole_driftregnskap_2015.json > kostra0a_grunnskole_driftregnskap_2015.json.bro
bro --quality 9 < hierarchy_agim.json > hierarchy_agim.json.bro
