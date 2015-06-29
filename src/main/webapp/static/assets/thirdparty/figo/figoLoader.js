/**
 * Created by figoxu on 15/4/20.
 */

window.figo = window.figo||{};

window.figo.loadStyleSheet = function(hrefLocation,identity){
    var style   = document.createElement( 'link' );
    style.rel   = 'stylesheet';
    style.type  = 'text/css';
    style.href  = hrefLocation;
    document.getElementById(identity).appendChild( style );
};