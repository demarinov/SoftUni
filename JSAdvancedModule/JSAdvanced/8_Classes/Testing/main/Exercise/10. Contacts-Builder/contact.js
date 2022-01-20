class Contact{
    constructor(firstName, lastName, phone, email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this._online = false;
    }

    get online() {return this._online;}

    set online(statusIn) {
        this._online = statusIn;

        if (this.divTitle) {
            if (statusIn) {
                this.divTitle.className = 'title online';
            } else {
                this.divTitle.className = 'title';
            }
        }
    }

    domFactory (tag, content = '') {
		const e = document.createElement(tag);
		e.innerHTML = content;
		return e;
	}

    render(id) {
        
        this.article = this.domFactory('article');
        this.divInfo = this.domFactory('div',
        `<span>&phone; ${this.phone}</span><span>&#9993; ${this.email}</span>`);

        this.divInfo.style.display = 'none';
        this.divInfo.className = 'info';

        this.divTitle = this.domFactory('div',`${this.firstName} ${this.lastName}`);
		this.divTitle.className = this.online ? 'title online' : 'title'
        this.divTitleButton = this.domFactory('button', '&#8505;');

		this.divTitle.appendChild(this.divTitleButton);
		this.article.appendChild(this.divTitle);
		this.article.appendChild(this.divInfo);

		document.getElementById(id).appendChild(this.article);
        

        this.divTitleButton.addEventListener('click', (e) => {

            if (this.divInfo.style.display === 'none') {
                this.divInfo.style.display = 'block';
            } else {
                this.divInfo.style.display = 'none';
            }

        });
    }
}


let data = {
    firstName: 'Ivan',
    lastName: 'Ivanov',
    phone: '0888 123 456',
    email: 'i.ivanov@gmail.com'
};

let contact = new Contact(data.firstName, data.lastName, data.phone, data.email);

contact.render('main');



let contacts = [
    new Contact("Ivan", "Ivanov", "0888 123 456", "i.ivanov@gmail.com"),
    new Contact("Maria", "Petrova", "0899 987 654", "mar4eto@abv.bg"),
    new Contact("Jordan", "Kirov", "0988 456 789", "jordk@gmail.com")
  ];
  contacts.forEach(c => c.render('main'));
  
  // After 1 second, change the online status to true
  setTimeout(() => contacts[2].online = true, 2000);


  class ContactV2 {
	constructor (f, l, p, e) {
		this.firstName = f
		this.lastName = l
		this.phone = p
		this.email = e
		this._online = false
	}

	get online () {
		return this._online
	}

	set online (v) {
		this._online = v

		if (this.onlineDiv) {
			this.onlineDiv.className = this._online ? 'title online' : 'title'
		}
	}

	eFactory (tag, content = '') {
		const e = document.createElement(tag)
		e.innerHTML = content

		return e
	}

	render (id) {
		this.templ = this.eFactory('article')
		this.onlineDiv = this.eFactory('div', `${this.firstName} ${this.lastName}`)
		this.infoBtn = this.eFactory('button', '&#8505;')
		this.infoDiv =
			this.eFactory(
				'div',
				`<span>&phone; ${this.phone}</span><span>&#9993; ${this.email}</span>`
			)


		this.onlineDiv.className = this.online ? 'title online' : 'title'
		this.infoDiv.className = 'info'
		this.infoDiv.style.display = 'none'

		this.onlineDiv.appendChild(this.infoBtn)
		this.templ.appendChild(this.onlineDiv)
		this.templ.appendChild(this.infoDiv)

		document.getElementById(id).appendChild(this.templ)

		this.infoBtn.addEventListener('click', () => {
			this.infoDiv.style.display = this.infoDiv.style.display === 'none' ? 'block' : 'none'
		})
	}
}
  