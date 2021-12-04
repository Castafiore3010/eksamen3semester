import "https://unpkg.com/navigo"  //Will create the global Navigo object used below
import anime from '../node_modules/animejs/lib/anime.es.js';
//const anime = require('animejs');


import {
    renderText, adjustForMissingHash, loadTemplate, renderTemplate,
} from "../utils.js"
import {setActiveLink} from "../utils.js";



window.addEventListener("load", async () => {


    function animateText() {
        let txt = document.getElementById('overTitle');
        let animation = anime({
            targets: txt,
            translateX: {
                value: [1600, 0],
                duration: 2000,
            },
            duration: 2000,
            easing: 'easeInOutExpo',
            delay: 250
        })
    }
    function animateBox() {

        let btn = document.getElementById('toolBoxOpener');
        let animation = anime({
            targets: btn,
            translateX: {
                value: [1600, 0],
                duration: 2000,
            },
            rotate: {
                value: 360,
                duration: 3000,
                easing : 'easeInOutSine',
            },
            borderRadius: [50, 0],
            duration: 2000,
            easing: 'easeInOutExpo',
            delay: 250
        })


        console.log("TRYNA ANIMATE")
    }



    function openToolBox() {
        document.getElementById('toolBoxOpener').onclick = async () => {
            let toolbox = document.getElementById('toolbox');
            if (toolbox.style.display === "block") {
                toolbox.style.display = "none";
            } else {
                toolbox.style.display = "block";
            }

        }
    }


    function handleMap() {

        document.getElementById('content').onmouseover = async (evt) => {

            let map = document.getElementById('map');
            console.log(map.id);
            console.log("x POS: " + evt.pageX + ", y: POS: " + evt.pageY)

            //handle map click.
            map.onclick = async (evt) => {
                // load marker
                const markerTemplate = await loadTemplate("templates/markerTemplate.html")
                renderTemplate(markerTemplate, "marker")
                const marker = document.getElementById('marker');

                // remove marker
                marker.onclick = async () => {
                    marker.style.display = "none";
                }

                // display marker on mouse position
                marker.style.display = "inline";
                marker.style.position = "absolute";
                marker.style.left = evt.pageX.toString() + "px";
                marker.style.top = evt.pageY.toString() + "px";
                marker.style.zIndex = "100000";
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
                console.log("router working");
                makeActive('homeLink');
                renderTemplate(templateEmpty, "content");


                },
            "/tours" : () => {
                renderTemplate(templateTours, "content");
            },
            "/map" :  (match) => {
                //renderTemplate(templateHome, "content");
                renderTemplate(leafletTemplate, "content");
                console.log(match)
                scrollTo();

                //handleMap();
                //document.getElementById('frontBtn').href = "";
                document.getElementById('frontBtn').addEventListener("click" ,async () => {
                    scrollTo();
                })
               makeActive('mapLink');
                openToolBox();
                test();
                animateText()
                animateBox();
            }

        })
        .notFound(() => renderText("No page for this route found", "content"))
        .resolve()


});

