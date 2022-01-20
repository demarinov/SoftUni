function printDeckOfCards(cards) {

  let outputCards = [];
  for(const cardData of cards) {
    const f = cardData.substr(0, cardData.length-1);
    const s = cardData.substr(cardData.length-1);

    try {
      const card = createCard(f,s);
      outputCards.push(card.toString());
    } catch (e) {
      console.log(e.message);
      return;
    }

    
  }

  console.log(outputCards.join(' '));

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
      if (!v[i]) throw new Error('Invalid card: '+f+s);
      return i;
    }

    let [face, suit] = [setter(validCardFaces, f), setter(suitMap, s)];

    const card = { face, suit, toString: () => { return face + suitMap[suit]; } };

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
  };
}

function testDeck() {

  printDeckOfCards(['AS', '10D', 'KH', '2C']);
  printDeckOfCards(['5S', '3D', 'QD', '1C']);
}

testDeck();
