/**
 * @private
 * Provides a layer slider control. This class is not currently supported by the deCarta Mobile JavaScript API.
 */
deCarta.UI.LayerControl = function(opt){    
    deCarta.UI.MapControl.call(this, opt);
}
deCarta.UI.LayerControl.prototype = {

    ready: false,

    initElements: function(){

        this.domElement = document.createElement('div');
        this.domElement.style.position = 'absolute';
        this.contentElement = document.createElement('div');
        this.contentElement.className = 'deCarta-control-layer';
        if(this.options.cssClass && this.options.cssClass !== this.contentElement.className){
            this.contentElement.className = this.contentElement.className + ' ' + this.options.cssClass;
        }        
        this.domElement.className = "domElement";

        this.streetBtn = document.createElement('div'); 
        this.streetBtn.className = 'button street selected';
        deCarta.Touch.attachListener('tap', this.streetBtn, function(){
                this.options.map.setStreetView() 
        }.bind(this), true);

        this.satelliteBtn = document.createElement('div');
        deCarta.Touch.attachListener('tap', this.satelliteBtn, function(){
            if(document.getElementById("hybridCheck").checked)
                this.options.map.setHybridView() 
            else
                this.options.map.setSatelliteView() 
        }.bind(this), true);
        this.satelliteBtn.className = 'button satellite';

        this.hybridDiv = document.createElement('div');
        this.hybridCheck = document.createElement('input');
        this.hybridCheck.type="checkbox"
        this.hybridCheck.id="hybridCheck"
        this.hybridDiv.appendChild(this.hybridCheck)
        this.hybridDiv.innerHTML+=" Labels"

        var hybridMode = false;

        this.hybridDiv.className = 'hybrid';

        var clear = document.createElement('div');
        clear.style.clear = 'both';

        this.contentElement.appendChild(this.streetBtn);
        this.contentElement.appendChild(this.satelliteBtn);

        this.contentElement.appendChild(this.hybridDiv);
        this.contentElement.appendChild(clear);

        this.domElement.appendChild(this.contentElement);
      
        this.options.map.onviewchange(function(ev){
            switch (ev.view){
                case 'street':
                    this.streetBtn.className = 'button street selected';
                    this.satelliteBtn.className = 'button satellite';
                    this.hybridDiv.style.display="none"
                break;
                case 'satellite':
                    this.streetBtn.className = 'button street';
                    this.satelliteBtn.className = 'button satellite selected';
                    this.hybridDiv.style.display="block"
                break;
                case 'hybrid':
                    this.streetBtn.className = 'button street';
                    this.satelliteBtn.className = 'button satellite selected';
                    this.hybridDiv.style.display="block"
                break;
            }
        }.bind(this));

        this.ready = true;
    },

    render: function(container){        
        if (!this.ready) this.initElements();
        container.appendChild( this.domElement );

        // added this after DOM insertion
        document.getElementById("hybridCheck").onchange=function(){
            if(document.getElementById("hybridCheck").checked){
                this.options.map.setHybridView() 
            }else{
                this.options.map.setSatelliteView() 
            }
        }.bind(this)
        this.width = this.domElement.offsetWidth;
        this.height = this.domElement.offsetHeight;          
    }
	
	
}; //end LayerControl prototype

//Extend the MapControl with the additional methods for LayerControl
deCarta.UI.LayerControl.prototype = deCarta.Utilities.inherit(deCarta.UI.LayerControl.prototype, deCarta.UI.MapControl.prototype);
