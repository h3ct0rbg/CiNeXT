class MyHeader extends HTMLElement {
    connectedCallback(){
        this.innerHTML = `
            <header>
                <p class="menu"><i class="fas fa-bars"></i></p>
                <div class="zoom">
                    <a class="logo" href="/Cine/areaUsuario.jsp"><img src="img/logo.svg" alt="logo"></a>
                </div>
                <nav>
                    <ul class="nav__links">
                        <li class="zoom"><a href="/Cine/areaUsuario.jsp">Peliculas</a></li>
                        <li class="zoom"><a href="#">Salas</a></li>
                        <li class="zoom"><a href="#">Estrenos</a></li>
                    </ul>
                </nav>
                <nav>
                    <div class="zoom">
                        <form action="Logout" method="get">
                            <button type="submit" class="cta">Cerrar Sesión</button>
                        </form>
                    </div>
                </nav>
            </header>

            <div class="overlay">
                <a class="close">&times;</a>
                <div class="overlay__content">
                    <a href="/Cine/areaUsuario.jsp">Peliculas</a>
                    <a href="#">Salas</a>
                    <a href="#">Estrenos</a>
                </div>
            </div>
        `
    }
}

customElements.define('my-header', MyHeader);

class MyHeaderAdmin extends HTMLElement {
    connectedCallback(){
        this.innerHTML = `
            <header>
                <p class="menu"><i class="fas fa-bars"></i></p>
                <div class="zoom">
                    <a class="logo" href="/Cine/areaUsuario.jsp"><img src="img/logo.svg" alt="logo"></a>
                </div>
                <nav>
                    <ul class="nav__links">
                        <li class="zoom"><a href="adminPeliculas.jsp">Peliculas</a></li>
                        <li class="zoom"><a href="#">Salas</a></li>
                        <li class="zoom"><a href="#">Entradas</a></li>
                        <li class="zoom"><a href="#">Reservas</a></li>
                        <li class="zoom"><a href="#">Informes</a></li>
                    </ul>
                </nav>
                <nav>
                    <div class="zoom">
                        <form action="Logout" method="get">
                            <button type="submit" class="cta">Cerrar Sesión</button>
                        </form>
                    </div>
                </nav>
            </header>

            <div class="overlay">
                <a class="close">&times;</a>
                <div class="overlay__content">
                    <a href="#">Peliculas</a>
                    <a href="#">Salas</a>
                    <a href="#">Entradas</a>
                    <a href="#">Reservas</a>
                    <a href="#">Informes</a>
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