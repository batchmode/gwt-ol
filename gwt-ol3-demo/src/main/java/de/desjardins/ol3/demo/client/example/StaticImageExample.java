package de.desjardins.ol3.demo.client.example;

import ol.AttributionOptions;
import ol.Attribution;
import de.desjardins.ol3.demo.client.utils.DemoUtils;
import ol.Collection;
import ol.FeatureOverlayOptions;
import ol.layer.Base;
import ol.source.ImageStatic;
import ol.source.ImageStaticOptions;
import ol.style.Style;
import ol.style.StyleOptions;
import ol.Map;
import ol.MapOptions;
import ol.OLFactory;
import ol.View;
import ol.ViewOptions;
import ol.layer.Image;
import ol.layer.LayerOptions;
import ol.proj.Projection;
import ol.proj.ProjectionOptions;

/**
 * Example with a StaticImage layer.
 * 
 * @author Tino Desjardins
 *
 */
public class StaticImageExample implements Example {

    /* (non-Javadoc)
     * @see de.desjardins.ol3.demo.client.example.Example#show()
     */
    @Override
    public void show() {
        
        ProjectionOptions projectionOptions = OLFactory.createOptions();
        
        double[] imageExtent = OLFactory.createExtent(0, 0, 1024, 968);
        
        projectionOptions.code("pixel")
          .extent(imageExtent)
          .units("pixels");
        
        Projection projection = OLFactory.createProjection(projectionOptions);
        
        ImageStaticOptions imageStaticOptions = OLFactory.createOptions();
        imageStaticOptions.url("http://imgs.xkcd.com/comics/online_communities.png")
            .imageSize(OLFactory.createSize(1024, 968))
            .imageExtent(imageExtent)
            .projection(projection);
        
        AttributionOptions attributionOptions = AttributionOptions.newInstance()
                .html("&copy; <a href=\"http://xkcd.com/license.html\">xkcd</a>");
        
        Attribution attribution = Attribution.newInstance(attributionOptions);
        Attribution[] attributions = new Attribution[1];
        attributions[0] = attribution;
        
        imageStaticOptions.setAttributions(attributions);
        
        ImageStatic imageStatic = OLFactory.createImageStaticSource(imageStaticOptions);
        
        LayerOptions layerOptions = OLFactory.createLayerOptions();
        layerOptions.setSource(imageStatic);
        Image image = OLFactory.createImageLayer(layerOptions);
        
        Collection<Base> layers = OLFactory.createCollection();
        layers.push(image);
        
        ViewOptions viewOptions = OLFactory.createOptions();
        viewOptions.setCenter(OLFactory.createCoordinate(500, 500));
        viewOptions.setProjection(projection);
        viewOptions.setZoom(2);
        
        View view = OLFactory.createView(viewOptions);
        
        MapOptions mapOptions = OLFactory.createOptions();
        mapOptions.setTarget("map");
        mapOptions.setView(view);
        mapOptions.setLayers(layers);
        
        Map map = Map.newInstance(mapOptions);
        
        // add some controls
        DemoUtils.addDefaultControls(map.getControls());
        
        Style style = Style.newInstance(StyleOptions.newInstance());
        
        FeatureOverlayOptions featureOverlayOptions = FeatureOverlayOptions.newInstance();
        featureOverlayOptions.setMap(map);
        featureOverlayOptions.setStyle(style);
        
    }
    
}

