# BPMN Check

## Static Verification
| ID | Question | Answer |
| - | - | - |
| 1 | Are all activities clearly described? Are they easy to understand? ||
| 2 | Is there any activity which the description should provide more information? ||
| 3 | Does each activity exclusively provide the sufficient and necessary information to be correctly understood? Is there any unnecessary information, although eventually correct? ||
| 4 | Does any activity describe operational details out of the business process modeling context, such as software algorithms or tasks performed and managed by third-parties? ||
| 5 | Does any activity in the model represent, indeed, a process event? ||
| 6 | Should any activity be dismembered into two or more activities (or even into a new process) to be in compliance with the abstraction level followed by the model? ||
| 7 | Does any activity refer to a nonexistent process/subprocess? ||
| 8 | Should any activity be represented as a process/subprocess call? ||
| 9 | Are the model pools and lanes clearly described and free from unnecessary information? Is it possible to identify each one in the business process description? ||
| 10 | Is there any activity associated with an incorrect pool/lane? ||
| 11 | Does each annotation is clearly and correctly described, containing only relevant information regarding the business process? ||
| 12 | Is there any annotation in the model that should be represented as an event or even as an activity? ||

## Process Flow Verification
| ID | Question | Answer |
| - | - | - |
| 13  | Does each sequence composed of two activities can be clearly interpreted, allowing the understanding of the whole business process described in the model? Is there no confusing or incomplete sequence of activities? ||
| 14  | Is each sequence of activities in compliance with the business process? ||
| 15  | Is there any activity in the model out of the process scope? ||
| 16  | Is there any activity relevant to the business process omitted from the model? ||
| 17  | Should any decision gateway (exclusive, inclusive or complex) in the model be in fact represented as a parallel gateway or vice versa? ||
| 18  | Is there any fork, join or merge between two or more activity flows that should not be synchronized (controlled)? Is there any fork, join or merge in the model that should be synchronized? ||
| 19  | Are all gates (conditions) required in the decision gateways correctly provided (clearly described and free of unnecessary information)? Are all these conditions feasible from the business process’ point of view? ||
| 20  | Is there any redundant default gate in the model, since the other gates from the same gateway can cover all possible alternatives? ||
| 21  | Is there any exclusive gateway composed by a non-exclusive set of gates? ||
| 22  | Is there any exclusive event-based gateway in which one or more event-based gates are in fact simple data-based gates? ||
| 23  | Is there any exclusive event-based gateway composed of a single event-based gate? ||
| 24  | Are there concurrent activities also represented as non-concurrent ones in the model? ||
| 25  | Are all conditional, error, cancellation, timer, message, link and signal events clearly and correctly described in the model? ||
| 26  | Is there any event of the business process omitted from the model? ||
| 27  | Should any catching event be characterized as a throwing event (or vice versa)? ||
| 28  | Are all events in the model associated with a suitable event type? ||
| 29  | Is it possible to identify the source of each signal event and link received by all activities in the model? ||
| 30  | Is there any signal event that should be better characterized as a message event? ||
| 31 |  Is there any error event that should be better characterized as a cancellation event? ||
| 32 |  Do all error events adequately represent errors in the business process execution? ||
| 33  | Are all technology for sending-receiving messages correctly described in the model events? ||
| 34  | Is there any timer event not effectively based on a temporal condition? Is there any timer event based on cyclic temporal condition although the business process had specified a specific date/time to it happens (or vice-versa)? ||
| 35  | Is there any multiple events in the model not connected to two or more distinct process events? ||
| 36  | Should be any conditional event in the model implemented as a decision gateway or vice versa? ||
| 37 |  Are all compensation events associated with the right compensation activities? ||
| 38  | Is there any pool/lane neglected in the model, omitting (for instance) a department, role or area described in the business process? ||
| 39  | Are there similar/identical activities in the model performed by different pools/ lanes?||
| 40 | Do all model transactions support all BPMN conditions to be characterized as that?||
| 41 | Does each loop sub-process present only the tasks that should be repeated under the conditions given?||
| 42 | Are all loop sub-processes conditions correctly provided (clearly described and free of unnecessary information)? Are all these conditions feasible from the business process?||
| 43 | Is there any serial loop sub-process that should be implemented as parallel or vice versa?||
| 44 | Is there any incomplete ad hoc activity or even incorrectly classified as ad-hoc?||
| 45 | Does each group highlighted in the model is composed of elements from the same category, helping to understand the business process better?||
| 46  |Taking into account all combinations of events and gates presented in the model, can we ensure that all possible scenarios of the process execution are correct?||
| 47  |Is there any inconsistency among the model elements? For instance, does any subsequence of activities contradict another subsequence or even does any gateway from the model gateway contradict another one?||
| 48  |Is there a clear correlation between the process model element in a lower level of abstraction and another model describing the same process in a higher level of abstraction?||

## Data Objects Verification
| ID | Question | Answer |
| - | - | - |
| 49  |Are all data objects in the model part of the business process? Are they clearly described without unnecessary/redundant information?||
| 50  |Is there any missing data object in the model?||
| 51  |Are all data objects represented in the model generated, consulted or modified by the activities associated with them? Do the directions of the arrows in such associations correctly represent such behaviors?||
| 52  |Is it possible to clearly understand each data object property/state? Is there no unnecessary or redundant information in such descriptions?||
| 53  |Is there any relevant data object property/state omitted from the model?||
| 54  |Is there two similar sub-flows (or more) in the model in which the data object properties/states diverge?||
| 55  |Do the possible model execution scenarios suggest any divergence with a data object state/property?||
