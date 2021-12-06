let animalList = [
    {name: "Lille Vandsalamander", spring: true, summer: true, autumn: true, winter: false, description: "Lille vandsalamander er en lille padde med lang krop"},
    {name: "Grønbroget Tudse", spring: false, summer: true, autumn: false, winter:true, description: "Grønbroget tudse har en sydøstlig udbredelse i Danmark."}
]
function compareObjects(object1, object2, key) {
    const obj1 = object1[key].toUpperCase()
    const obj2 = object2[key].toUpperCase()

    if (obj1 < obj2) {
        return -1
    }
    if (obj1 > obj2) {
        return 1
    }
    return 0
}

export function checkCurrentSeason() {
    let currentSeasonHeader = document.getElementById("seasonID1");
    let today = new Date();
    let month = today.getMonth();
    let boolSpring = month >= 3 && month <= 5;
    let boolSummer = month >= 6 && month <= 8;
    let boolAutumn = month >= 9 && month <= 11;
    //let boolWinter = month == 12 || month == 1 || month == 2;
    if(boolSpring) {
        // Current season is spring
        currentSeasonHeader.innerText = "Forår"

    } else if (boolSummer) {
        currentSeasonHeader.innerText = "Sommer"
    }
    else if (boolAutumn) {
        currentSeasonHeader.innerText = "Efterår"
        animalList.filter(a=>a.summer)
        .sort((a,b) => {
            return compareObjects(a,b,'name')
        })
        .map(a => console.log(a.name))
    } else {
        currentSeasonHeader.innerText = "Vinter"
    }
}

export function changeSeasonView() {
    let currentSeasonHeader = document.getElementById("seasonID1");
    let allSeasonHeader = document.getElementById("seasonID2");
}

/* Dyr
Lille Vandsalamander: marts - juli, april - maj, *HI* i oktober til marts?
Grønbroget Tudse: april - juni,
 */