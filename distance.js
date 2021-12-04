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
    const KILOMETERS_PER_NAUTICAL_MILE = 1.85200;
    let lat1Rad = degrees_to_radians(lat1);
    let lng1Rad = degrees_to_radians(lng1);
    let lat2Rad = degrees_to_radians(lat2);
    let lng2Rad = degrees_to_radians(lng2);


    // Great circle distance in radians, using law of cosines
    let angle = Math.acos(Math.sin(lat1Rad) * Math.sin(lat2Rad) + Math.cos(lat1Rad) * Math.cos(lat2Rad)
        * Math.cos(lng1Rad - lng2Rad));


    let nauticalMiles = 60 * radians_to_degrees(angle);
    // return distance as kilometers
    return KILOMETERS_PER_NAUTICAL_MILE * nauticalMiles;

}
let lat1 = 55.74077370039102;
let lat2 = 12.258970407344417;
let lng1 = 55.73471871488309;
let lng2 = 12.24945187085078;

function test() {
    let result = distanceBetween(lat1, lat2, lng1, lng2);
    console.log(result);
}
test();
