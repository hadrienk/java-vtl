#!/bin/sh

###
# ========================LICENSE_START=================================
# Java VTL
# %%
# Copyright (C) 2016 - 2017 Hadrien Kohl
# %%
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
# 
#      http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
# =========================LICENSE_END==================================
###

# kostra0a_2015.json
#curl -q -s -H"Accept: application/ssb.dataset+json" "http://al-kostra-app-utv:7080/api/v2/data/KOSTRA0A:425215?access_token=[TOKENHERE]&columns=PERIODE,BYDEL,KONTOKLASSE,REGION,ART_SEKTOR,FUNKSJON_KAPITTEL,BELOP&sort=PERIODE,BYDEL,REGION,FUNKSJON_KAPITTEL,KONTOKLASSE,ART_SEKTOR" | pv | jq "[.data[] | [.[5]] + .[1:5] + .[6:8]]" > kostra0a_2015.json


jq "[.[] | select(.[3] == \"202\" and .[4] == \"1\")]" < kostra0a_2016.json > kostra0a_grunnskole_driftregnskap_2016.json
jq "[.[] | select(.[3] == \"202\" and .[4] == \"1\")]" < kostra0a_2015.json > kostra0a_grunnskole_driftregnskap_2015.json

# Compress
bro --quality 9 < kostra0a_2015.json > kostra0a_2015.json.bro
bro --quality 9 < kostra0a_2016.json > kostra0a_2016.json.bro
bro --quality 9 < kostra0a_grunnskole_driftregnskap_2016.json > kostra0a_grunnskole_driftregnskap_2016.json.bro
bro --quality 9 < kostra0a_grunnskole_driftregnskap_2015.json > kostra0a_grunnskole_driftregnskap_2015.json.bro
bro --quality 9 < account_hierarchy.json > account_hierarchy.json.bro
