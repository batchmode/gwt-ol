package de.desjardins.ol3.demo.client.example;

import com.google.gwt.dom.client.Element;
import de.desjardins.ol3.demo.client.utils.DemoUtils;
import ol.Coordinate;
import ol.Map;
import ol.MapOptions;
import ol.OLFactory;
import ol.OLUtil;
import ol.View;
import ol.control.Attribution;
import ol.interaction.KeyboardPan;
import ol.interaction.KeyboardZoom;
import ol.layer.LayerOptions;
import ol.layer.Tile;
import ol.source.Osm;
import ol.source.TileDebug;
import ol.source.TileDebugOptions;
import ol.source.XyzOptions;

/**
 * Example with OSM layer and tile debug layer.
 * 
 * @author Tino Desjardins
 *
 */
public class OsmExample implements Example {

    /* (non-Javadoc)
     * @see de.desjardins.ol3.demo.client.example.Example#show()
     */
    @Override
    public void show(Element element) {
        
        // create a OSM-layer
        XyzOptions osmSourceOptions = OLFactory.createOptions();
        
        Osm osmSource = new Osm(osmSourceOptions);
        LayerOptions osmLayerOptions = OLFactory.createOptions();
        osmLayerOptions.setSource(osmSource);
        
        Tile osmLayer = new Tile(osmLayerOptions);

        // create debug layer
        TileDebugOptions tileDebugOptions = OLFactory.createOptions();
        tileDebugOptions.setProjection("EPSG:3857");
        tileDebugOptions.setTileGrid(osmSource.getTileGrid());

        TileDebug tileDebugSource = new TileDebug(tileDebugOptions);
        
        LayerOptions tileDebugLayerOptions = OLFactory.createOptions();
        
        tileDebugLayerOptions.setSource(tileDebugSource);
        
        Tile tileDebugLayer = new Tile(tileDebugLayerOptions);
        
        
        // create a view
        View view = new View();

        Coordinate centerCoordinate = OLFactory.createCoordinate(-0.1275, 51.507222);
        Coordinate transformedCenterCoordinate = OLUtil.transform(centerCoordinate, "EPSG:4326", "EPSG:3857"); 
        
        view.setCenter(transformedCenterCoordinate);
        view.setZoom(10);

        // create the map
        MapOptions mapOptions = OLFactory.createOptions();
        mapOptions.setTargetElement(element);
        mapOptions.setView(view);

        Map map = new Map(mapOptions);
        
        map.addLayer(osmLayer);
        map.addLayer(tileDebugLayer);

        // add some controls
        map.addControl(OLFactory.createScaleLine());
        DemoUtils.addDefaultControls(map.getControls());
        
        Attribution attribution = new Attribution();
        attribution.setCollapsed(true);
        
        map.addControl(attribution);
        
        // add some interactions
        map.addInteraction(new KeyboardPan());
        map.addInteraction(new KeyboardZoom());
     
    }
    
}

