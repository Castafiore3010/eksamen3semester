import "https://unpkg.com/navigo"  //Will create the global Navigo object used below

import {
    renderText, adjustForMissingHash, loadTemplate, renderTemplate,
} from "../utils.js"
import {setActiveLink} from "../utils.js";


window.addEventListener("load", async () => {
    function handlers() {
        handleMap();
    }
    handlers();

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
            }
        }
    }


    const templateHome = await loadTemplate("templates/home.html");
    const templateTours = await loadTemplate("templates/tours.html");

    const router = new Navigo("/", {hash : true});

    adjustForMissingHash();
    router
        .hooks({
            before(done, match) {
                setActiveLink("topnav", match.url)
                done()
            }
        })
        .on({
            "/": () => {
                renderTemplate(templateHome, "content")
                console.log("router working");
            },
            "/tours" : () => {

                renderTemplate(templateTours, "content");
            },
            "/map" : () => {
                renderTemplate(templateHome, "content");
            }

        })
        .notFound(() => renderText("No page for this route found", "content"))
        .resolve()


});
