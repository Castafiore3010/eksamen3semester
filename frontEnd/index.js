import "https://unpkg.com/navigo"  //Will create the global Navigo object used below

import {
    renderText, adjustForMissingHash, loadTemplate, renderTemplate,
} from "../utils.js"
import {setActiveLink} from "../utils.js";


window.addEventListener("load", async () => {


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

    const templateEmpty = await loadTemplate("templates/emptyTemplate.html")
    const templateHome = await loadTemplate("templates/home.html");
    const templateTours = await loadTemplate("templates/tours.html");

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
                renderTemplate(templateHome, "content");
                console.log(match)
                scrollTo();
                handleMap();
                //document.getElementById('frontBtn').href = "";
                document.getElementById('frontBtn').addEventListener("click" ,async () => {
                    scrollTo();
                })
               makeActive('mapLink');
            }

        })
        .notFound(() => renderText("No page for this route found", "content"))
        .resolve()


});

