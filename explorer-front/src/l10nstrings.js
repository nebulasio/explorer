/*
The idea is simple: this is a JSON file containing root elements for each
language. In turn, each root element contains all the string definitions needed
in each component or route.

Inside the .vue files, you'll find that, in most cases, a localizable DOM
element is defined with a class (for example, dashboardlocalizable), an id
(for example, dashboardTitle), and, in some cases, a localize attribute that
indicates which attribute it must localize. If not present, it will localize the
inner text of that DOM element.

Sometimes you will need to localize an element that is created dynamically and
is repeated over and over again (that's the case of the ticks in the dashboard).
In such cases, instead of using the mentioned class, you need to use another
one, for example, dashboardmultilocalizable. In addition to this, you need to
use the name attribute (and the localize attribute, in the same way as before).

For example, if you need to localize a <th> element, you can use the following
example:

	<th class="taxonlocalizable" id="taxonTableElement"></th>

and then, down below, you must add the following code in each language:

	"taxonTableElement": "Your localized string here"

Just as easy.

In some exceptional cases you'll find that neither case applies. In such cases,
you can still define your localized string here, and then use them in a custom
way in your component, making use of the computed, mounted or watched methods,
just by referring to this JSON as follows:

	jsonStrings[this.$selectedLanguage][elementId]

Keep in mind that you need to add two imports on your component:

	import { EventBus } from '../events.js';
	import { jsonStrings } from '../l10nstrings.js';

And then, add the following methods:

* checkStaticTranslations
* checkDynamicTranslations
* removeTempInterval

And the definitions on the mounted() method.

You can check the dashboard.vue file as a reference and a guide on how to do
this.

*/
import Vue from 'vue';
//Add more lines like those two for additional language strings
import { json_en_us } from './l10n_en_us.js';
import { json_es_es } from './l10n_es_es.js';
export const jsonStrings = {
	"en_US": json_en_us,
	"es_ES": json_es_es
};