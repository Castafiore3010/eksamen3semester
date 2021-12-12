import "https://unpkg.com/navigo" //Will create the global Navigo object used below
import anime from '../node_modules/animejs/lib/anime.es.js';
//const anime = require('animejs');
import {checkCurrentSeason} from "./dyrJS.js"
import {adjustForMissingHash, loadTemplate, renderTemplate, renderText,} from "../utils.js"
import {startSpil} from "./spil.js";


window.addEventListener("load", async () => {

    async function getOneTour(id) {
        let response = await fetch("http://localhost:7777/tours/"+id);
        let responseData = await response.json();
        return responseData;
    }

    async function createPin() {

        let pin = {};
        pin.pinId = null;
        pin.latitude = Number(document.getElementById('latitudeCreatePin').value);
        pin.longitude = Number(document.getElementById('longitudeCreatePin').value);
        pin.description = document.getElementById('descriptionCreatePin').value;
        pin.title = document.getElementById('titleCreatePin').value;
        console.log("TITEL : " + pin.title);
        pin.tours = [];
        pin.mediaLinks = [];
        //console.log(document.getElementById('toursCreatePin').dataset.dataNum.value);
        console.log(Number(document.getElementById('toursCreatePin').value.substring(0,1)))
        if (Number(document.getElementById('toursCreatePin').value.substring(0,1)) !== 0) {
            let tour = await getOneTour(Number(document.getElementById('toursCreatePin').value.substring(0, 1)));
            console.log(tour);
            let shortTour = {"tourId" : tour.tourId};
            pin.tours.push(shortTour);
        }
        console.log(pin);

        let options = makeOptions("POST", pin);

        let response = await fetch("http://localhost:7777/pins", options);
        let responseData = await response.json();
        allPins.push(pin);
        console.log(responseData);
    }

    function setUpSelectInputs() {
        let tour1 = allTours[0];
        console.log(tour1);
        let selectTours = allTours.map(tour => `<option data-data-optNum="${tour.tourId}">${tour.tourId} : ${tour.description}</option>`).join("");
        console.log("selectTours")
        console.log(selectTours);
        document.getElementById('toursCreatePin').onclick = () => {
            document.getElementById('toursCreatePin').innerHTML = selectTours;
        }

    }

    function setUpCreatePinForm() {


        let target = document.getElementById('createForm1');
        console.log(target.offsetWidth)
        console.log(target.offsetLeft);

        document.getElementById('createPinBtn').onclick = (evt) => {
            evt.stopPropagation();
            evt.preventDefault();
            createPin();

        }

        document.getElementById('resetForm').onclick = (evt) => {
            evt.preventDefault();
            evt.stopPropagation();
            document.getElementById('createPinForm').reset();
            document.getElementById('toursCreatePin').innerHTML = "";
        }
    }

    function animateCreateForm() {
        let target = document.getElementById('createForm1');
        console.log(target.offsetWidth)
        console.log(target.offsetLeft);

        let animation = anime({
            targets: target,
            translateX: {
                value: 0,
                duration: 0,
                delay: 0,

            },
            opacity : {
                value: [0, 1],
                duration: 3500,
                delay: 200
            },

            easing: 'easeInOutExpo'
        })

        console.log(target.offsetWidth)
        console.log(target.offsetLeft);
    }

    function openNav() {

        document.getElementById('openSidebarBtn').onclick = async () => {
            document.getElementById('sidebar').style.transition = "0.5s"
            document.getElementById('sidebar').style.overflow = "hidden";
            document.getElementById('sidebar').style.width = "260px";
            document.querySelectorAll('.sidebar a').forEach(link => {
            let animation = anime({
                targets: link,
                opacity: [0, 1],
                delay : 500,
                easing: 'linear'
            })
        })
        }
    }

    function closeNav() {
        document.getElementById('closeSidebarBtn').onclick = async () => {
            document.getElementById('sidebar').style.transition = "0.0s";
            document.getElementById('sidebar').style.width = "0";
        }

    }


    function setupAccordion() {
        document.querySelectorAll('.accordion__button').forEach(button => {
            button.addEventListener('click', () => {
                //const accordionContent = button.nextElementSibling;
                button.classList.toggle('accordion__button--activeAccordion');
                let currentScroll = window.scrollY;
                window.scrollTo(0, document.body.scrollHeight);
            })
        })

    }

    function setupAnimalSounds() {
        function AnimalSound(src) {
            this.sound = document.createElement('audio');
            this.sound.src = src;
            this.sound.setAttribute('preload', 'auto');
            this.sound.setAttribute('controls', 'none');
            this.sound.style.display = 'none';
            document.body.appendChild(this.sound)
            this.play = () => {this.sound.play();}
            this.stop = () => {this.sound.pause();}

        }

        let edderfuglSound = new AnimalSound("./images/duckSounds1.mp3");
        let toadSound = new AnimalSound("./images/frogSounds1.mp3");
        let sealSound = new AnimalSound("./images/sealSounds.mp3");
        let playButtons = document.querySelectorAll('.playBtn');
        let stopButtons = document.querySelectorAll('.pauseBtn');

        console.log(playButtons);
        playButtons.forEach(btn => {

                if (btn.dataset.dataSound === "seal") {
                    btn.addEventListener('click', async () => {
                        console.log("DEBUG SOUND: SEAL")
                        sealSound.play();
                    })
                }

                if(btn.dataset.dataSound === "bird") {
                    btn.addEventListener('click', async () => {
                        edderfuglSound.play();
                    })
                }

                if(btn.dataset.dataSound === "toad") {
                    btn.addEventListener('click', async () => {
                        toadSound.play();
                    })

                }

            });


        stopButtons.forEach(btn => {

            if (btn.dataset.dataSound === "seal") {
                btn.addEventListener('click', async () => {
                    console.log("DEBUG SOUND: SEAL")
                    sealSound.stop();
                })
            }

            if(btn.dataset.dataSound === "bird") {
                btn.addEventListener('click', async () => {
                    edderfuglSound.stop();
                })
            }

            if(btn.dataset.dataSound === "toad") {
                btn.addEventListener('click', async () => {
                    toadSound.stop();
                })

            }
        });
    }



    // FETCH FUNCTIONS



    async function fetchAllPins () {
        let array = []
        let response = await fetch("http://localhost:7777/pins");
        let responseData = await response.json()

        responseData.forEach(pin => {
            array.push(pin);
        })
        return array;
    }

    async function fetchAllTours () {
        let array = []
        let response = await fetch("http://localhost:7777/tours");
        let responseData = await response.json()

        responseData.forEach(tour => {
            array.push(tour)
        })
        return array;
    }







    // ANIMATIONS

    function animateBox() {

        let upperBox = document.getElementById('upperBox');
        let animation = anime({
            targets: upperBox,
            translateX: {
                value: [1600, 0],
                duration: 2500,
            },
            easing: 'easeInOutExpo',
            delay: 200
        });

    }
    function animateMap() {
        let map = document.getElementById('Lmap');
        let animation = anime({
            targets: map,
            translateX: {
                value: [1600, 0],
                duration: 2500,
            },
            easing: 'easeInOutExpo',
            delay: 200
        })
    }
    function animateBtnRotate() {
        let btn = document.getElementById('toolBoxOpener');
        let animation = anime({
                targets: btn,
                translateX: 0,
                rotate: {
                    value: 360,
                    duration: 1000,
                    easing: 'easeInOutSine',
                    delay: 500
                },
                easing: 'easeInOutExpo',
            });
        document.querySelector('.toolBoxOpener').onmouseenter = animation.play;






    }

    function animateAnimalArticles() {
        let bird = document.getElementById('edderfuglBtn');
        let toad = document.getElementById('toadBtn');
        let seal = document.getElementById('sealBtn');
        let textFragments = document.querySelectorAll('.accordion__content .accordion__content');


        let animateBird = anime({
            targets: bird,
            translateX: {
                value: [10000, 0],
                duration: 1000
            },
            easing: 'easeInOutExpo',
            delay: 200
        });

        let animateToad = anime({
            targets: toad,
            translateX: {
                value: [-10000, 0],
                duration: 1000
            },
            easing: 'easeInOutExpo',
            delay: 1200
        });

        let animateSeal = anime({
            targets: seal,
            translateX: {
                value: [10000, 0],
                duration: 1000
            },
            easing: 'easeInOutExpo',
            delay: 2200
        });

        let buttons = document.querySelectorAll('.accordion__button');

        buttons.forEach(btn => {
            btn.addEventListener('click', async () => {

            let target;

            switch(btn.id) {
                case "edderfuglBtn":
                    target = document.getElementById('birdText');
                    break;
                case "toadBtn":
                    target = document.getElementById('toadText');
                    break;
                case "sealBtn":
                    target = document.getElementById('sealText');
            }
            console.log("DEBUG TEXT ANIMATION")
                console.log(target.id);

            let animateText = anime({
                targets: target,
                opacity: [0, 1],
                scale: [0.2, 1],
                easing: 'easeInOutExpo',
                duration: 2000,
                delay: 200,

            })
            });

        });
    }




    // OPEN TOOLBOX
    function openToolBox() {
        document.getElementById('toolBoxOpener').onclick = async () => {
            let toolbox = document.getElementById('toolbox');
            if (toolbox.style.display === "block") {
                toolbox.style.display = "none";
                document.getElementById('toolBoxOpener').style.background = "#fff"
            } else {
                toolbox.style.display = "block";
                document.getElementById('toolBoxOpener').style.background = "#555"
            }

        }
    }



    function scrollTo() {
        window.scrollTo(0, 1500);
    }

    function makeActive(id) {
        let array = Array.from(document.getElementsByTagName('a'));
        document.getElementById(id).classList.add("active");
        array.filter(link => link.id !== id).forEach(link => link.classList.remove("active"));
    }

    //55.32073, 15.18601
    //55.32132, 15.18703
    //55.31933, 15.18896


    function makeOptions(method, body) {
        const opts = {
            method: method,
            headers: {
                "Content-type": "application/json",
                "Accept": "application/json"
            }
        }
        if (body) { //Observe how we can add new fields to an object when needed
            opts.body = JSON.stringify(body);
        }
        return opts;
    }

    let bridgeCounter = 0;
    async function changeBridgeStatus(id, status) {

        function Bridge(id, status) {
            this.id = id;
            this.status = status;
            this.observers = [];
        }
        Bridge.prototype = {
            subscribe: function(fn)
            {
                this.observers.push(fn);
            },
            unsubscribe: function (fnToRemove)
            {
                this.observers = this.observers.filter(fn => {
                    if (fn !== fnToRemove) {
                        return fn;
                    }
                })
            },
            fire: function ()
            {
                this.observers.forEach(fn => {
                    fn.call();
                })
            }

        }
        const bridge = new Bridge(id, status);
        bridge.subscribe(changeBridgeView);


        let options = makeOptions("PUT", bridge);
        let url = "http://localhost:7777/bridge/" + id;


        let response = await fetch(url, options);
        let body = await response.json();

        bridge.fire();
        console.log("Bridge status changed to : " + body.status);
        bridgeCounter++;

    }

    let setUpChangeCounter = 0;
    function setUpChange() {
        document.getElementById('changeBridge').onclick = async () => {
            if (setUpChangeCounter % 2 === 0) {
                await changeBridgeStatus(1, "closed");
                setUpChangeCounter++;
            } else {
                setUpChangeCounter++;
                await changeBridgeStatus(1, "open");
            }


        }
    }



    function changeBridgeView() {
        if (bridgeCounter % 2 === 0) {
            document.getElementById('bridgeManager').style.background = 'crimson'
            document.getElementById('bridgeManager').innerHTML = `<p>Bridge : Closed</p>`
        } else {
            document.getElementById('bridgeManager').style.background = 'darkseagreen'
            document.getElementById('bridgeManager').innerHTML = `<p>Bridge : Open</p>`
        }


    }

    function test() {
        let christinasCoorddsad15 = 15.189608459058762
        let christiansCoordi = 55.32098217829384
        let map = L.map('Lmap').setView([55.32098217829384, 15.189608459058762], 17);

        let tiles = L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=sk.eyJ1IjoibWljazM0NjAiLCJhIjoiY2t3cWhjN2lkMG1uYzJxdXMzY2psZGNrNCJ9.r5xaFY3G00Kb05VZRZ1GkQ', {
            attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
            maxZoom: 30,
            minZoom: 5,
            id: 'mapbox/streets-v11',
            tileSize: 512,
            zoomOffset: -1
        }).addTo(map);

        //map.scrollWheelZoom.disable();


        function mapclick(e) {
            console.log("coords : " + e.latlng);
            if (document.getElementById('latitudeCreatePin') !== null) {
                console.log(e)
                document.getElementById('latitudeCreatePin').value = e.latlng.lat
                document.getElementById('longitudeCreatePin').value = e.latlng.lng;
            }
        }

        function setupMapClick() {
            map.on('click', mapclick)

        }

        setupMapClick();


        let marker;
        let churchMarkers = [];

        if (document.getElementById('churchCheckBox') !== null) {
            document.getElementById('churchCheckBox').onclick = async (evt) => {

                let churchPins = allPins.filter(pin => pin.title == "Kirke");

                let icon = L.icon({
                    iconUrl: './images/church2.png',
                    iconSize: [48, 48],

                })

                if (document.getElementById('churchCheckBox').checked === true) {
                    churchPins.forEach(pin => {
                        marker = L.marker([pin.latitude, pin.longitude], {
                            icon: icon,
                            title: `${pin.title}`
                        }).addTo(map);
                        marker.bindPopup(`${pin.description}`);
                        churchMarkers.push(marker);
                    })


                } else {
                    churchMarkers.forEach(marker => {
                        map.removeLayer(marker);
                    })
                    churchMarkers = [];

                }


            }


            let localMarkers = [];
            let localRoute = [];
            let polyLines = [];
            let routeDistance = 0;
            let loopCounter = 0;
            document.getElementById('tour1Checkbox').onclick = async () => {

                //let allPins = await fetchAllPins();

                if (document.getElementById('tour1Checkbox').checked === true) {

                    allPins.filter(pin => {
                        if (pin.tours[0] != null) {
                            return (pin.tours[0] === 1 || pin.tours[0].tourId === 1)
                        }
                    })
                        .forEach(pin => {
                            let newMarker = L.marker([pin.latitude, pin.longitude]).addTo(map)
                            newMarker.bindPopup(`${pin.description}`);
                            if (pin.mediaLinks.length > 0) {
                                let customPopup = L.popup({
                                    maxWidth: 560,
                                    className: "popup"
                                })
                                    .setLatLng([pin.latitude, pin.longitude])
                                    .setContent(`<h4>${pin.title}</h4><br><br>${pin.description}<br><br>${pin.mediaLinks[0].mediaLink}`)
                                console.log(pin.mediaLinks);
                                newMarker.bindPopup(customPopup);

                            }
                            let coords = newMarker.getLatLng();
                            localRoute.push([coords.lat, coords.lng]);
                            console.log("NUM OF COORDS : " + localRoute.length);

                            localMarkers.push(newMarker);
                            console.log("NUM OF MARKERS : " + localMarkers.length);
                        });


                    console.log(localRoute);


                    /*

                   let routing =  L.Routing.control({
                        waypoints: [
                            [55.7034201, 12.5823192],
                            [55.38417, 10.30924]


                        ],
                        routeWhileDragging: true
                    }).addTo(map);




                     */


                    if (polyLines.length < 1) {
                        let newPolyLine = L.polyline(localRoute, {color: '#e38c12'}).addTo(map);
                        polyLines.push(newPolyLine);
                    } else {
                        polyLines[0].addTo(map);

                    }


                    for (let i = 0; i <= localRoute.length - 2; i++) {

                        let latlng = L.latLng(localRoute[i][0], localRoute[i][1]);
                        let next = L.latLng(localRoute[i + 1][0], localRoute[i + 1][1])
                        let distance = latlng.distanceTo(next);
                        console.log(distance);
                        routeDistance += distance;

                    }
                    loopCounter++;

                    let distanceTool = document.getElementById('distanceTool');

                    distanceTool.style.display = "flex";
                    distanceTool.innerHTML = `<p>Rute længde:  ~${Math.round(routeDistance)} meter</p>`;
                    console.log("DISTANCE : " + routeDistance);
                    routeDistance = 0;


                } else {
                    localMarkers.forEach(marker => map.removeLayer(marker));
                    polyLines.forEach(line => map.removeLayer(line));
                    document.getElementById('distanceTool').style.display = "none";


                }
                localRoute = [];
            }
        }
    }



    // ENTITY LOAD
    const allPins = await fetchAllPins();
    const allTours = await fetchAllTours();
    console.log(allPins);
    console.log(allTours);




    // TEMPLATE LOAD
    const templateEmpty = await loadTemplate("templates/emptyTemplate.html")
    const templateHome = await loadTemplate("templates/home.html");
    const templateTours = await loadTemplate("templates/tours.html");
    const leafletTemplate = await loadTemplate("templates/map/leafletTemplate.html")
    const templateDyr = await loadTemplate("templates/dyrTemplate.html")
    const templateBygninger = await loadTemplate("templates/bygningerTemplate.html")
    const templateSpil = await loadTemplate("templates/spilTemplate.html")
    const templateCreatePin = await loadTemplate("templates/pinForm.html")

    const router = new Navigo("/", {hash : true});

    adjustForMissingHash();
    router
        .on({
            "/": () => {
                makeActive('homeLink');
                renderTemplate(templateEmpty, "content");
                openNav();
                closeNav();
                },
            "/tours" : () => {
                renderTemplate(templateTours, "content");
            },
            "/map" :  (match) => {
                //renderTemplate(templateHome, "content");
                renderTemplate(leafletTemplate, "content");
                scrollTo();
                //handleMap();
                //document.getElementById('frontBtn').href = "";
                document.getElementById('frontBtn').addEventListener("click" ,async () => {
                    scrollTo();
                })
               makeActive('mapLink');
                openToolBox();
                test();
                //animateText()
                animateBox();
                animateMap();
                animateBtnRotate();
                setUpChange();
                if (match.params && match.params.tourId) {
                    let id = match.params.tourId;
                    console.log("DEBUG MATCH PARAMS");
                    console.log(allTours);
                    let matchingTour = allTours.filter(tour => tour.tourId == id);
                    console.log("matchingTour");
                    console.log(matchingTour);

                    //console.log(tour[tourId]);
                    let idRequest = `tour${matchingTour[0].tourId}Checkbox`;
                    console.log(idRequest);
                    document.getElementById(idRequest).click();

                }

            },
            "/dyr" : () => {
                makeActive('dyrLink');
                renderTemplate(templateDyr, "content");
                checkCurrentSeason();
                setupAccordion();// Changes current season title to season name
                scrollTo()
                animateAnimalArticles();
                setupAnimalSounds();
            },
            "/bygninger" : () => {
                makeActive('bygningerLink');
                renderTemplate(templateBygninger, "content");
            },
            "/spil" : () => {
                makeActive('spilLink');
                renderTemplate(templateSpil, "content")
                startSpil();
            },
            "/createPin" : (match) => {
                renderTemplate(templateCreatePin, "content");
                scrollTo();
                setUpCreatePinForm();
                setUpSelectInputs();
                test();
                animateCreateForm();


            }

        })
        .notFound(() => renderText("No page for this route found", "content"))
        .resolve()


});

