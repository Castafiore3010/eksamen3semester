import "https://unpkg.com/navigo"  //Will create the global Navigo object used below
import anime from '../node_modules/animejs/lib/anime.es.js';
//const anime = require('animejs');


import {
    renderText, adjustForMissingHash, loadTemplate, renderTemplate,
} from "../utils.js"
import {setActiveLink} from "../utils.js";



window.addEventListener("load", async () => {



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
            minZoom: 16,
            id: 'mapbox/streets-v11',
            tileSize: 512,
            zoomOffset: -1
        }).addTo(map);
        map.scrollWheelZoom.disable();




        /*
        let latlng = [
            [55.32073, 15.18601],
            [55.32132, 15.18703],
            [55.31933, 15.18896]
        ];
        const polyline = L.polyline(latlng, {color: 'red'}).addTo(map);
        map.fitBounds(polyline.getBounds());

         */


        let marker;
        document.getElementById('churchCheckBox').onclick = async () => {

            let icon = L.icon({
                iconUrl: './images/church2.png',
                iconSize: [48,48],

            })

            if (document.getElementById('churchCheckBox').checked === true) {
                marker = L.marker([55.32132, 15.18703], {icon : icon, title: "Kirke"}).addTo(map);
                marker.bindPopup("Christiansø Kirke");


            } else {
                map.removeLayer(marker);
            }


        }


    }

    const templateEmpty = await loadTemplate("templates/emptyTemplate.html")
    const templateHome = await loadTemplate("templates/home.html");
    const templateTours = await loadTemplate("templates/tours.html");
    const leafletTemplate = await loadTemplate("templates/leafletTemplate.html")

    const router = new Navigo("/", {hash : true});

    adjustForMissingHash();
    router
        .on({
            "/": () => {

                makeActive('homeLink');
                renderTemplate(templateEmpty, "content");



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

            }

        })
        .notFound(() => renderText("No page for this route found", "content"))
        .resolve()


});

