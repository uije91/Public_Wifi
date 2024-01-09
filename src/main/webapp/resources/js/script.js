function getLocation() {
    navigator.geolocation.getCurrentPosition(function (pos) {
        let lat = pos.coords.latitude;
        let lnt = pos.coords.longitude;

        document.getElementById('latitude').value = lat;
        document.getElementById('longitude').value = lnt;
    });
}

function getNearbyWifiList() {
    let lat = document.getElementById("latitude").value;
    let lnt = document.getElementById("longitude").value;

    if (lat === "0.0" && lnt === "0.0" || lat === "" && lnt === "") {
        alert("위치 정보를 입력해주세요.");
    } else {
        window.location.assign("http://localhost:8081?lat=" + lat + "&lnt=" + lnt);
    }
}

