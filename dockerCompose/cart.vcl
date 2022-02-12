vcl 4.1;

import directors;

backend app1 {
    .host = "shoppingcart_service";
    .port = "8080";
}

backend app2 {
    .host = "shoppingcart_service_2";
    .port = "8080";
}

sub vcl_init {
    new vdir = directors.round_robin();
    vdir.add_backend(app1);
    vdir.add_backend(app2);
}

sub vcl_recv {
    set req.backend_hint = vdir.backend();
}

sub vcl_hit {
    return (pass);
}
