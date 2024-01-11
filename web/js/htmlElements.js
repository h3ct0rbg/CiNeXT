class MyHeader extends HTMLElement {
    connectedCallback(){
        this.innerHTML = `
            <header>
                <p class="menu"><i class="fas fa-bars"></i></p>
                <div class="zoom">
                    <a class="logo" href="/Cine"><img src="/Cine/img/logo.svg" alt="logo"></a>
                </div>
                <nav>
                    <ul class="nav__links">
                        <li class="zoom"><a href="/Cine">Peliculas</a></li>
                        <li class="zoom"><a href="#">Salas</a></li>
                        <li class="zoom"><a href="#">Estrenos</a></li>
                    </ul>
                </nav>
                <nav>
                    <div class="zoom">
                        <form action="/Cine/login" method="get">
                            <button type="submit" class="cta">Iniciar Sesión</button>
                        </form>
                    </div>
                </nav>
            </header>

            <div class="overlay">
                <a class="close">&times;</a>
                <div class="overlay__content">
                    <a href="/Cine">Peliculas</a>
                    <a href="#">Salas</a>
                    <a href="#">Estrenos</a>
                </div>
            </div>
        `
    }
}

customElements.define('my-header', MyHeader);

class MyHeaderLogged extends HTMLElement {
    connectedCallback(){
        this.innerHTML = `
            <header>
                <p class="menu"><i class="fas fa-bars"></i></p>
                <div class="zoom">
                    <a class="logo" href="/Cine"><img src="/Cine/img/logo.svg" alt="logo"></a>
                </div>
                <nav>
                    <ul class="nav__links">
                        <li class="zoom"><a href="/Cine">Peliculas</a></li>
                        <li class="zoom"><a href="#">Salas</a></li>
                        <li class="zoom"><a href="#">Estrenos</a></li>
                    </ul>
                </nav>
                <nav>
                    <div class="zoom">
                        <form action="/Cine/logout" method="get">
                            <button type="submit" class="cta">Cerrar Sesión</button>
                        </form>
                    </div>
                </nav>
            </header>

            <div class="overlay">
                <a class="close">&times;</a>
                <div class="overlay__content">
                    <a href="/Cine">Peliculas</a>
                    <a href="#">Salas</a>
                    <a href="#">Estrenos</a>
                </div>
            </div>
        `
    }
}

customElements.define('my-header-logged', MyHeaderLogged);

class MyHeaderAdmin extends HTMLElement {
    connectedCallback(){
        this.innerHTML = `
            <header>
                <p class="menu"><i class="fas fa-bars"></i></p>
                <div class="zoom">
                    <a class="logo" href="/Cine"><img src="/Cine/img/logo.svg" alt="logo"></a>
                </div>
                <nav>
                    <ul class="nav__links">
                        <li class="zoom"><a href="/Cine/admin/peliculas">Peliculas</a></li>
                        <li class="zoom"><a href="/Cine/admin/salas">Salas</a></li>
                        <li class="zoom"><a href="/Cine/admin/sesiones">Sesiones</a></li>
                        <li class="zoom"><a href="/Cine/admin/entradas">Entradas</a></li>
                        <li class="zoom"><a href="/Cine/admin/informes">Informes</a></li>
                    </ul>
                </nav>
                <nav>
                    <div class="zoom">
                        <form action="/Cine/logout" method="get">
                            <button type="submit" class="cta">Cerrar Sesión</button>
                        </form>
                    </div>
                </nav>
            </header>

            <div class="overlay">
                <a class="close">&times;</a>
                <div class="overlay__content">
                    <a href="/Cine/admin/peliculas">Peliculas</a>
                    <a href="/Cine/admin/salas">Salas</a>
                    <a href="/Cine/admin/sesiones">Sesiones</a>
                    <a href="/Cine/admin/entradas">Entradas</a>
                    <a href="/Cine/admin/informes">Informes</a>
                </div>
            </div>
        `
    }
}

customElements.define('my-header-admin', MyHeaderAdmin);

class MyFooter extends HTMLElement {
    connectedCallback(){
        this.innerHTML = `
            <footer>
                <p>&copy; 2023 CiNeXT</p>
            </footer>
        `
    }
}

customElements.define('my-footer', MyFooter);