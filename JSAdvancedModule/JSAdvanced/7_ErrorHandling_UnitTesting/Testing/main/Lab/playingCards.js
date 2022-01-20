function createCard(f, s) {

    const validCardFaces = {
        '2': true, '3': true, '4': true, '5': true, '6': true,
        '7': true, '8': true, '9': true, '10': true,
        'J': true, 'Q': true, 'K': true, 'A': true
    };

    // valid suits in the map ...                       
    const suitMap = {

        'S': '\u2660',
        'H': '\u2665',
        'D': '\u2666',
        'C': '\u2663'
    }

    const setter = (v, i) => {
        if (!v[i]) throw new Error()
        return i;
    }

    let [face, suit] = [setter(validCardFaces, f), setter(suitMap, s)];

    const card = { face, suit, toString: () => {return face + suitMap[suit];} };

    Object.defineProperties(card, {
        face: {
            get() { return face; },
            set: (v) => { setter(validCardFaces, v) }
        },
        suit: {
            get() { return suit },
            set: (s) => setter(suitMap, s)
        },
    });

    return card;
}

function test() {

    let card = createCard('A', 'S');
    console.log(card.toString());

    card = createCard('10', 'H');
    console.log(card.toString());

    // card = createCard('1', 'C');
    console.log(card.toString());

    // card = createCard('2', 'F');
    console.log(card.toString());

    // card.face = 1;
    console.log(card.toString());

}

test();

function test2() {
    let card = cardFactory('A', 'S');

    console.log(card.toString());

    card = cardFactory('10', 'H');
    console.log(card.toString());

    // card = cardFactory('1', 'C');
    console.log(card.toString());

    // card = cardFactory('2', 'F');
    console.log(card.toString());

    card.face = 1;
    console.log(card.toString());
}

function cardFactory(f, s) {


    const faces = {
        2: 2, 3: 3, 4: 4, 5: 5, 6: 6, 7: 7, 8: 8, 9: 9, 10: 10, J: 'J', Q: 'Q', K: 'K', A: 'A',
    }
    const suits = { S: '\u2660', H: '\u2665', D: '\u2666', C: '\u2663', }
    const setter = (c, e) => {
        if (!c[e]) throw new Error()
        return c[e]
    }
    let [face, suit] = [setter(faces, f), setter(suits, s)]

    const card = { face, suit, toString: () => `${face}${suit}` }

    Object.defineProperties(card, {
        face: {
            get() { return face },
            set: (f) => setter(faces, f)
        }, suit: {
            get() { return suit },
            set: (s) => setter(suits, s)
        },

    })

    console.log(card);
    return card
}

module.exports = { cardFactory }