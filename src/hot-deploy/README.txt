
This is a hot-deploy component directory.
All components in this tree will be loaded after the EasyFK components, including those in:
core
themes
baseapps
 

The hot-deploy Auto-Loading feature loads all components in the order they are found (i.e. alphabetic or creation date).

If you need a specific loading order of these components then you need to disable the Auto-Loading feature 
by creating a component-load.xml file in the hot-deploy directory and use the load-component tag to load 
your components in the order you want (just use the component-load.xml file in the application folder as a template).


