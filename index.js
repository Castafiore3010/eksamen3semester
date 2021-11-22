import "https://unpkg.com/navigo"  //Will create the global Navigo object used below

import {
    renderText, adjustForMissingHash, loadTemplate, renderTemplate,
} from "./utils.js"
import {setActiveLink} from "./utils.js";


window.addEventListener("load", async () => {


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

