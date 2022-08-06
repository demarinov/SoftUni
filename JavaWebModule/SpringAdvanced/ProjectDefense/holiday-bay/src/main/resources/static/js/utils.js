

async function getRoomData(roomType) {

    let response = await fetch("http://localhost:7777/rooms?type="+roomType);

    if (response.status === 200) {
        let data = await response.json();

        console.log(data);
        return data;
    } else {
        console.log(response.status);
    }

    return null;
}

async function getHotelByRoomId(roomId) {

    let response = await fetch("http://localhost:7777/hotels/"+roomId);

    if (response.status === 200) {
        let data = await response.json();

        console.log(data);
        return data;
    } else {
        console.log(response.status);
    }

    return null;
}

export {
    getRoomData, getHotelByRoomId
};