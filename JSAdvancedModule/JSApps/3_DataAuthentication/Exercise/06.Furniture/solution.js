function solve() {

  const currentPageUrl = window.location.href;
  const pageName = currentPageUrl.substring(currentPageUrl.lastIndexOf("/")+1);

  const pageFunctionMapper = {

      'home.html': doHome,
      'login.html':doLogin,
      'homeLogged.html':doHomeLogged
  }

  if (pageFunctionMapper[pageName] !== undefined) {
    pageFunctionMapper[pageName]();
  }
}

function doHomeLogged() {
  console.log('home logged');

  const loggedOutBtn = document.querySelector('#logoutBtn');

  loggedOutBtn.addEventListener('click',logout);

  const formBtn = document.querySelector('form button');

  formBtn.addEventListener('click', createFurniture);

  getAllFurnitures(true);

  const buyBtn = document.querySelector('.col-md-12>button');

  buyBtn.addEventListener('click', makeOrder);

  const ordersBtn = document.querySelector('.orders button');

  ordersBtn.addEventListener('click', getAllOrders);

  // clear the bought items
  
  getAllOrders(null);


}

async function getAllOrders(e) {

  const orders = await getAllOrdersGetRequest();

  if (orders === undefined || orders.length === 0) {
    console.log('No orders yet!');
    return;
  }

  let furnitureNames = [];
  let totalPrice = 0;

  orders.forEach(o => {
    furnitureNames.push(o[0].name);
    totalPrice += Number(o[0].price);
  });

  const orderSpans = Array.from(document.querySelectorAll('.orders span'));

  orderSpans[0].textContent = orderSpans[0].textContent+', '+furnitureNames.join(', ');
  totalPrice += Number(orderSpans[1].textContent.split(' ')[0]);
  orderSpans[1].textContent = totalPrice;
}

async function getAllOrdersGetRequest() {

  const user = JSON.parse(sessionStorage.getItem('user'));
  const userId = {
    "userId":user.id
  }
  const response = await fetch('http://localhost:3030/data/orders?_ownerId%3D'+JSON.stringify(user.id), {
        method: 'get',
        headers: {
            'Content-Type': 'application/json',
            'X-Authorization':sessionStorage.getItem('authToken')
        }
    });

    if (response.status == 200) {
        const data = await response.json();
        return data;
    } else {
        console.error('Error: Unable to fetch catches!');
    }
}

async function makeOrder(e) {

  // get all checked furnitures
  const furnitureTable = Array.from(document.querySelectorAll('.table tbody tr'));

  const orders = [];

  furnitureTable.forEach(fRow => {

    const fColMarked = Array.from(Array.from(fRow.children)[4].children)[0];

    if (fColMarked.checked) {
      
      const fColName = Array.from(Array.from(fRow.children)[1].children)[0];
      const fColPrice = Array.from(Array.from(fRow.children)[2].children)[0];
      const user = JSON.parse(sessionStorage.getItem('user'));
      const order = {
        "name": fColName.textContent,
        "price":fColPrice.textContent,
        "_ownerId":user.id
      };

      orders.push(order);

      fColMarked.checked = false;
    }
  });


  // send orders
  sendOrdersPostRequest(orders);
}

async function sendOrdersPostRequest(orders) {

  const response = await fetch('http://localhost:3030/data/orders', {
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            'X-Authorization':sessionStorage.getItem('authToken')
        },
        body: JSON.stringify(orders)
    });

    if (response.status == 200) {
        const data = await response.json();
        return data;
    } else {
        console.error('Error: Unable to fetch catches!');
    }
}

async function createFurniture(ev) {
  ev.preventDefault();

  console.log('create furniture');

  const formName = document.querySelector('form input[name=name]');
  const formPrice = document.querySelector('form input[name=price]');
  const formFactor = document.querySelector('form input[name=factor]');
  const formImage = document.querySelector('form input[name=img]');

  if (formName.value === '' || formPrice.value === ''
  || formFactor.value === '' || formImage.value === '') {
    console.error('Empty input fields!');
    return;
  }

  // add new row to the table
  const furnitures = [];
  const furniture = {
    "name":formName.value,
    "price":formPrice.value,
    "factor": formFactor.value,
    "src": formImage.value
  };

  furnitures.push(furniture);
  
  addFurnitures(furnitures, true);

  // send post request

  await sendCreateFurniturePostRequest(furniture);

  // clear input
  formName.value = '';
  formPrice.value = '';
  formFactor.value = '';
  formImage.value = '';
}

async function sendCreateFurniturePostRequest(data) {
  const response = await fetch('http://localhost:3030/data/furniture', {
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
            'X-Authorization':sessionStorage.getItem('authToken')
        },
        body: JSON.stringify(data)
    });

    if (response.status == 200) {
        const data = await response.json();
        return data;
    } else {
        console.error('Error: Unable to fetch catches!');
    }
}

async function logout(e) {

  const token = sessionStorage.getItem('authToken');

    if (token != null) {
        sendLogoutRequest('http://localhost:3030/users/logout', token);
    }
}

async function sendLogoutRequest(url, token) {

  const result = await fetch(url, {
      method: 'delete',
      headers: {
          'X-Authorization': token
      }

  });

  if (result.status >= 200 && result.status < 300) {
      let path = window.location.pathname;
      path = path.replace(/homeLogged.html/, 'home.html');
      sessionStorage.removeItem('authToken');
      sessionStorage.removeItem('user');
      window.location.pathname = path;

  } else {
      console.error(result.statusText);
  }

}

function doLogin() {
  console.log('doLogin');

  const formButtons = document.querySelectorAll('form button');

  const registerBtn = formButtons[0];
  const loginBtn = formButtons[1];

  loginBtn.addEventListener('click',login);

  registerBtn.addEventListener('click',register);
  
}

async function register(ev) {

  ev.preventDefault();

  const formData = new FormData(ev.target.parentElement);

        const email = formData.get('email');
        const password = formData.get('password');
        const repeatPass = formData.get('rePass');

        if (repeatPass !== password) {
          console.error('Passwords do not match!');
          return;
        }

      await sendRegistrationRequest('http://localhost:3030/users/register'
            , { email, password});
        
      // clear form data    
      ev.target.parentElement.reset();  
}

async function sendRegistrationRequest(url, data) {


  const result = await fetch(url, {
      method: "post",
      headers: {
          "Content-Type": "application/json"
      },
      body: JSON.stringify(data)
  });

  
  if (result.status === 200) {
      const response = await result.json();
      const data = response;
      console.log('success');
      const authToken = data.accessToken;
      sessionStorage.setItem('authToken', authToken);
      sessionStorage.setItem('user', 
      JSON.stringify({'email':data.email, 'id':data['_id']}));
      let path = window.location.pathname;
      path = path.replace(/login.html/, 'homeLogged.html');
      console.log(path);
      window.location.pathname = path;

  } else {
      console.error('Error: Unable to register!');
  }


}

async function login(ev) {
  ev.preventDefault();
  const formData = new FormData(ev.target.parentElement);

        const email = formData.get('email');
        const password = formData.get('password');

    const data = {
        "email": email,
        "password":password
    }

    await sendPostLoginRequest(data);

    // clear form fields
    ev.target.parentElement.reset();
}

async function sendPostLoginRequest(data) {

  const response = await fetch('http://localhost:3030/users/login', {
      method: 'post',
      headers: {
          'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
  });

  if (response.status == 200) {
      const data = await response.json();
      console.log(data);
      sessionStorage.setItem('authToken', data.accessToken);
      sessionStorage.setItem('user', 
      JSON.stringify({'email':data.email, 'id':data['_id']}));
      let path = window.location.pathname;
      path = path.replace(/login.html/, 'homeLogged.html');
      window.location.pathname = path;
  } else {
      console.error('Error: Unable to login!');
  }

}

function doHome() {


  getAllFurnitures(false);
}

async function getAllFurnitures(logged) {

  const furnitures = await sendGetFurnitures();

  // create furniture table
  addFurnitures(furnitures, logged);
}

function addFurnitures(furnitures, logged) {

  const furnitureTable = document.querySelector('.table tbody');

  if (furnitures === undefined || furnitures.length === 0) {
    console.log('No furnitures yet!');
    return;
  }

  furnitures.forEach(f => {

    // image, name, price, decoration factor, mark
    const rowFurniture = createElement('tr', '', '');
    const colImage = createElement('td', '', '');
    const colImageValue = createElement('img', '', '');
    colImageValue.src = f.src;
    colImage.appendChild(colImageValue);
    rowFurniture.appendChild(colImage);

    const colName = createElement('td', '', '');
    const colNameValue = createElement('p', '', f.name);
    colName.appendChild(colNameValue);
    rowFurniture.appendChild(colName);

    const colPrice = createElement('td', '', '');
    const colPriceValue = createElement('p', '', f.price);
    colPrice.appendChild(colPriceValue);
    rowFurniture.appendChild(colPrice);

    const colDecFactor = createElement('td', '', '');
    const colDecFactorValue = createElement('p', '', f.factor);
    colDecFactor.appendChild(colDecFactorValue);
    rowFurniture.appendChild(colDecFactor);

    const colMark = createElement('td', '', '');
    const colMarkValue = createElement('input', '', '');
    colMarkValue.type = 'checkbox';
    if (!logged) {
      colMarkValue.disabled = true;
    } else {
      colMarkValue.disabled = false;
    }

    colMark.appendChild(colMarkValue);
    rowFurniture.appendChild(colMark);

    furnitureTable.appendChild(rowFurniture);
  });


}

function createElement(type, clazz, value) {

  const element = document.createElement(type);

  if (clazz != '') {

    element.className = clazz;
  }

  element.value = value;
  element.textContent = value;

  return element;
}



async function sendGetFurnitures() {

  const response = await fetch('http://localhost:3030/data/furniture', {
    method: 'get',
    headers: {
      "Content-Type": "application/json"
    }
  }).catch(e => console.log(e));

  if (response === undefined) {
    console.error('Error: no response!');
    return;
  }

  if (response.status === 200) {

    const data = await response.json();

    return data;
  } else {
    console.error(response.status + ": " + JSON.stringify(response.text));
  }
}