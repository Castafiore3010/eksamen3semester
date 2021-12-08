export function startSpil() {
    let radioform1 = document.getElementById('q1').elements['q1'];
    let radioform2 = document.getElementById('q2').elements['q2'];
    let radioform3 = document.getElementById('q3').elements['q3'];
    document.getElementById("sub").onclick = (evt) => { //
        evt.preventDefault()
        let correctAnswers = 0;
        if(radioform1.value == "q1a1") {
            correctAnswers++;
        }
        if(radioform2.value == "q2a2") {
            correctAnswers++;
        }
        if(radioform3.value == "q3a3") {
            correctAnswers++;
        }
        let status = document.getElementById("status");
        status.innerText = "Du fik " + correctAnswers + " svar korrekt!"
        console.log(correctAnswers)
    }
}