<html>
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark" aria-label="Third navbar example">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Restaurant</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsExample03">
                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="/api/" class="nav-link px-2 text-white">Home</a></li>
                    <li><a href="/api/app/menu" class="nav-link px-2 text-white">Menu</a></li>
                    <li><a href="/api/app/myorders" class="nav-link px-2 text-white">My order</a></li>
                    <li><a href="/api/app/admin/orders" class="nav-link px-2 text-white">Orders</a></li>
                    <li><a href="/api/app/admin/users" class="nav-link px-2 text-white">Users</a></li>
                </ul>
                <div class="text-end">
                    <div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
                        <ul class="navbar-nav">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">
                                    ENG
                                </a>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                                    <li><a href="?lang=en" class="dropdown-item">Eng</a></li>
                                    <li><a href="?lang=ua" class="dropdown-item">Ua</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <button type="button" class="btn btn-outline-light me-2" onclick="location.href='/api/app/login';">Login</button>
                    <button type="button" class="btn btn-warning" onclick="location.href='/api/app/registration';">Sign-in</button>
                    <button type="button" class="btn btn-outline-light me-2" onclick="location.href='/api/app/logout';">Logout</button>
                </div>
            </div>
        </div>
    </nav>
</html>
