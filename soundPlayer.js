let audio = new Audio('TomSigma.wav');

function playSound(audio) {

    audio.pause();
    audio.currentTime = 0;


    audio.play();

}

playSound(audio);