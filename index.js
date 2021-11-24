import "https://unpkg.com/navigo"  //Will create the global Navigo object used below

import {
    renderText, adjustForMissingHash, loadTemplate, renderTemplate,
} from "./utils.js"
import {setActiveLink} from "./utils.js";


window.addEventListener("load", async () => {

    function radians_to_degrees(radians)
    {
        let pi = Math.PI;
        return radians * (180/pi);
    }

    function degrees_to_radians(degrees)
    {
        let pi = Math.PI;
        return degrees * (pi/180);
    }

    function distanceBetween(lat1, lng1, lat2, lng2) {
        const KILOMERTERS_PER_NAUTICAL_MILE = 1.85200;
        let lat1Rad = degrees_to_radians(lat1);
        let lng1Rad = degrees_to_radians(lng1);
        let lat2Rad = degrees_to_radians(lat2);
        let lng2Rad = degrees_to_radians(lng2);


        // Great circle distance in radians, using law of cosines

        let angle = Math.acos(Math.sin(lat1Rad) * Math.sin(lat2Rad) + Math.cos(lat1Rad) * Math.cos(lat2Rad)
            * Math.cos(lng1Rad - lng2Rad));


        let nauticalMiles = 60 * radians_to_degrees(angle);
        // return distance as kilometers
        return KILOMERTERS_PER_NAUTICAL_MILE * nauticalMiles;

    }

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
                const markerTemplate = await loadTemplate("./markerTemplate.html")
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


    const templateHome = await loadTemplate("./home.html");
    const templateTours = await loadTemplate("./tours.html");


    const router = new Navigo("/", {hash : true});

    adjustForMissingHash();
    router
        .hooks({


        })
        .on({
            "/": () => {
                renderTemplate(templateHome, "content")
                console.log("router working");
            },
            "/tours" : () => {
                toggleMenu();
                renderTemplate(templateTours, "content");
            }

        })
        .notFound(() => renderText("No page for this route found", "content"))
        .resolve()


});

