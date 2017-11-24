<li class="dropdown-toggle">
    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
        Login
        <span class="caret"></span>
    </button>

    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
        <li>
            <form class="form" role="form" method="post" action="/VirtualRealityGladiators/j_spring_security_check" accept-charset="UTF-8" id="login-nav">
                <div class="form-group">
                    <input name="j_username" type="text" class="form-control" id="loginUsername" 
                           style="min-width: 200px" placeholder="Username" required>
                </div>
                <div class="form-group">
                    <input name="j_password" type="password" class="form-control" id="loginPassword"
                           style="min-width: 200px" placeholder="Password" required>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block" style="background-color: darkgreen; border-color: green">Sign in</button>
                </div>
            </form>
        </li>
    </ul>
</li>